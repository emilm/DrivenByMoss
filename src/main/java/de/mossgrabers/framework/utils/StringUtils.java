// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.utils;

import java.nio.ByteBuffer;


/**
 * Helper class for string methods.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class StringUtils
{
    private static final char [] REMOVABLE_CHARS =
    {
        ' ',
        'e',
        'a',
        'u',
        'i',
        'o'
    };


    /**
     * Construcotr, private due to help class.
     */
    private StringUtils ()
    {
        // Intentionally empty
    }


    /**
     * Pad the given text with the given character until it reaches the given length.
     *
     * @param str The text to pad
     * @param length The maximum length
     * @param character The character to use for padding
     * @return The padded text
     */
    public static String pad (final String str, final int length, final char character)
    {
        final String text = str == null ? "" : str;
        final int diff = length - text.length ();
        if (diff < 0)
            return text.substring (0, length);
        if (diff > 0)
        {
            final StringBuilder sb = new StringBuilder (text.length () + diff).append (text);
            for (int i = 0; i < diff; i++)
                sb.append (character);
            return sb.toString ();
        }
        return text;
    }


    /**
     * First replaces umlauts with alternative writing, then shortens a text to the given length.
     *
     * @param text The text to shorten
     * @param length The length to shorten to
     * @return The shortened text
     */
    public static String shortenAndFixASCII (final String text, final int length)
    {
        return optimizeName (fixASCII (text), length);
    }


    /**
     * Replace umlauts with alternative writing.
     *
     * @param name The string to check
     * @return The string with replaced umlauts
     */
    public static String fixASCII (final String name)
    {
        if (name == null)
            return null;
        final StringBuilder str = new StringBuilder ();
        for (int i = 0; i < name.length (); i++)
        {
            final char c = name.charAt (i);
            if (c > 127)
            {
                switch (c)
                {
                    case 'Ä':
                        str.append ("Ae");
                        break;
                    case 'ä':
                        str.append ("ae");
                        break;
                    case 'Ö':
                    case '\u0152':
                        str.append ("Oe");
                        break;
                    case 'ö':
                    case '\u0153':
                        str.append ("oe");
                        break;
                    case 'Ü':
                        str.append ("Ue");
                        break;
                    case 'ü':
                        str.append ("ue");
                        break;
                    case 'ß':
                        str.append ("ss");
                        break;
                    case 'é':
                        str.append ("e");
                        break;
                    case '→':
                        str.append ("->");
                        break;
                    default:
                        str.append ("?");
                        break;
                }
            }
            else
                str.append (c);
        }
        return str.toString ();
    }


    /**
     * Shortens a text to the given length.
     *
     * @param text The text to shorten
     * @param length The length to shorten to
     * @return The shortened text
     */
    public static String optimizeName (final String text, final int length)
    {
        if (text == null)
            return "";

        String shortened = text;
        for (final char element: REMOVABLE_CHARS)
        {
            if (shortened.length () <= length)
                return shortened;
            int pos;
            while ((pos = shortened.indexOf (element)) != -1)
            {
                shortened = shortened.substring (0, pos) + shortened.substring (pos + 1, shortened.length ());
                if (shortened.length () <= length)
                    return shortened;
            }
        }
        return shortened.length () <= length ? shortened : shortened.substring (0, length);
    }


    /**
     * Convert the bytes to a hex string.
     *
     * @param data The data to convert
     * @return The hex string
     */
    public static String toHexStr (final int [] data)
    {
        final StringBuilder sysex = new StringBuilder ();
        for (final int d: data)
            sysex.append (toHexStr (d)).append (' ');
        return sysex.toString ();
    }


    /**
     * Convert the bytes to a hex string. Rewinds the buffer and adds the bytes from the beginning
     * till the capacity.
     *
     * @param data The data to convert
     * @return The hex string
     */
    public static String toHexStr (final ByteBuffer data)
    {
        final StringBuilder sysex = new StringBuilder ();
        while (data.position () < data.limit ())
            sysex.append (toHexStr (Byte.toUnsignedInt (data.get ()))).append (' ');
        return sysex.toString ();
    }


    /**
     * Convert the bytes to a hex string
     *
     * @param data The data to convert
     * @return The hex string
     */
    public static String toHexStr (final byte [] data)
    {
        final StringBuilder sysex = new StringBuilder ();
        for (final byte d: data)
            sysex.append (toHexStr (Byte.toUnsignedInt (d))).append (' ');
        return sysex.toString ();
    }


    /**
     * Convert the byte to a hex string
     *
     * @param number The value to convert
     * @return The hex string
     */
    public static String toHexStr (final int number)
    {
        return String.format ("%02X", Integer.valueOf (number));
    }


    /**
     * Parse a byte from an hex encoded string. A byte has 2 digits in the string.
     *
     * @param data The data in hex
     * @param index The index of the byte
     * @return The parsed byte as integer
     */
    public static int fromHexStr (final String data, final int index)
    {
        final int pos = index * 2;
        return Integer.parseInt (data.substring (pos, pos + 2), 16);
    }


    /**
     * Convert a string with hex encoded bytes. One byte is 2 characters without any spaces.
     *
     * @param data The data to convert
     * @return The parsed byte array
     */
    public static int [] fromHexStr (final String data)
    {
        final int length = data.length ();
        if (length % 2 != 0)
            throw new IllegalArgumentException ("Length of hex data must be a multiple of 2!");

        final int size = length / 2;
        final int [] result = new int [size];
        for (int i = 0; i < size; i++)
        {
            final int pos = i * 2;
            result[i] = Integer.parseInt (data.substring (pos, pos + 2), 16);
        }
        return result;
    }


    /**
     * Format the given time as measure.quarters.eights.
     *
     * @param quartersPerMeasure The number of quarters of a measure
     * @param time The time to format
     * @param startOffset An offset that is added to the measure, quarter and eights values
     * @return The formatted text
     */
    public static String formatMeasures (final int quartersPerMeasure, final double time, final int startOffset)
    {
        final int measure = (int) Math.floor (time / quartersPerMeasure);
        double t = time - measure * quartersPerMeasure;
        final int quarters = (int) Math.floor (t); // :1
        t = t - quarters; // *1
        final int eights = (int) Math.floor (t / 0.25);
        return measure + startOffset + "." + (quarters + startOffset) + "." + (eights + startOffset);
    }
}
