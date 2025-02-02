// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.controller.sl.controller;

import de.mossgrabers.controller.sl.SLConfiguration;
import de.mossgrabers.framework.controller.AbstractControlSurface;
import de.mossgrabers.framework.controller.color.ColorManager;
import de.mossgrabers.framework.controller.grid.PadGridImpl;
import de.mossgrabers.framework.daw.IHost;
import de.mossgrabers.framework.daw.midi.IMidiInput;
import de.mossgrabers.framework.daw.midi.IMidiOutput;


/**
 * The Novation SLmkI and SLmkII control surface.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
@SuppressWarnings("javadoc")
public class SLControlSurface extends AbstractControlSurface<SLConfiguration>
{
    public static final int     MKII_KNOB_ROW2_1           = 8;
    public static final int     MKII_KNOB_ROW2_2           = 9;
    public static final int     MKII_KNOB_ROW2_3           = 10;
    public static final int     MKII_KNOB_ROW2_4           = 11;
    public static final int     MKII_KNOB_ROW2_5           = 12;
    public static final int     MKII_KNOB_ROW2_6           = 13;
    public static final int     MKII_KNOB_ROW2_7           = 14;
    public static final int     MKII_KNOB_ROW2_8           = 15;
    public static final int     MKII_SLIDER1               = 16;
    public static final int     MKII_SLIDER2               = 17;
    public static final int     MKII_SLIDER3               = 18;
    public static final int     MKII_SLIDER4               = 19;
    public static final int     MKII_SLIDER5               = 20;
    public static final int     MKII_SLIDER6               = 21;
    public static final int     MKII_SLIDER7               = 22;
    public static final int     MKII_SLIDER8               = 23;
    public static final int     MKII_BUTTON_ROW1_1         = 24;
    public static final int     MKII_BUTTON_ROW1_2         = 25;
    public static final int     MKII_BUTTON_ROW1_3         = 26;
    public static final int     MKII_BUTTON_ROW1_4         = 27;
    public static final int     MKII_BUTTON_ROW1_5         = 28;
    public static final int     MKII_BUTTON_ROW1_6         = 29;
    public static final int     MKII_BUTTON_ROW1_7         = 30;
    public static final int     MKII_BUTTON_ROW1_8         = 31;
    public static final int     MKII_BUTTON_ROW2_1         = 32;
    public static final int     MKII_BUTTON_ROW2_2         = 33;
    public static final int     MKII_BUTTON_ROW2_3         = 34;
    public static final int     MKII_BUTTON_ROW2_4         = 35;
    public static final int     MKII_BUTTON_ROW2_5         = 36;
    public static final int     MKII_BUTTON_ROW2_6         = 37;
    public static final int     MKII_BUTTON_ROW2_7         = 38;
    public static final int     MKII_BUTTON_ROW2_8         = 39;
    public static final int     MKII_BUTTON_ROW3_1         = 40;
    public static final int     MKII_BUTTON_ROW3_2         = 41;
    public static final int     MKII_BUTTON_ROW3_3         = 42;
    public static final int     MKII_BUTTON_ROW3_4         = 43;
    public static final int     MKII_BUTTON_ROW3_5         = 44;
    public static final int     MKII_BUTTON_ROW3_6         = 45;
    public static final int     MKII_BUTTON_ROW3_7         = 46;
    public static final int     MKII_BUTTON_ROW3_8         = 47;
    public static final int     MKII_BUTTON_ROW4_1         = 48;
    public static final int     MKII_BUTTON_ROW4_2         = 49;
    public static final int     MKII_BUTTON_ROW4_3         = 50;
    public static final int     MKII_BUTTON_ROW4_4         = 51;
    public static final int     MKII_BUTTON_ROW4_5         = 52;
    public static final int     MKII_BUTTON_ROW4_6         = 53;
    public static final int     MKII_BUTTON_ROW4_7         = 54;
    public static final int     MKII_BUTTON_ROW4_8         = 55;
    public static final int     MKII_KNOB_ROW1_1           = 56;
    public static final int     MKII_KNOB_ROW1_2           = 57;
    public static final int     MKII_KNOB_ROW1_3           = 58;
    public static final int     MKII_KNOB_ROW1_4           = 59;
    public static final int     MKII_KNOB_ROW1_5           = 60;
    public static final int     MKII_KNOB_ROW1_6           = 61;
    public static final int     MKII_KNOB_ROW1_7           = 62;
    public static final int     MKII_KNOB_ROW1_8           = 63;
    // This is also the crossfader on the Zero
    public static final int     MKII_TOUCHPAD_X            = 68;
    public static final int     MKII_TOUCHPAD_Y            = 69;
    public static final int     MKII_BUTTON_REWIND         = 72;
    public static final int     MKII_BUTTON_FORWARD        = 73;
    public static final int     MKII_BUTTON_STOP           = 74;
    public static final int     MKII_BUTTON_PLAY           = 75;
    public static final int     MKII_BUTTON_RECORD         = 76;
    public static final int     MKII_BUTTON_LOOP           = 77;
    public static final int     MKII_BUTTON_TRANSPORT      = 79;
    public static final int     MKII_BUTTON_ROWSEL1        = 80;
    public static final int     MKII_BUTTON_ROWSEL2        = 81;
    public static final int     MKII_BUTTON_ROWSEL3        = 82;
    public static final int     MKII_BUTTON_ROWSEL4        = 83;
    public static final int     MKII_BUTTON_ROWSEL5        = 84;
    public static final int     MKII_BUTTON_ROWSEL6        = 85;
    public static final int     MKII_BUTTON_ROWSEL7        = 86;
    public static final int     MKII_BUTTON_ROWSEL8        = 87;
    // Page left on the Zero
    public static final int     MKII_BUTTON_P1_UP          = 88;
    // Page right on the Zero
    public static final int     MKII_BUTTON_P1_DOWN        = 89;
    // Preview + Page left on the Zero
    public static final int     MKII_BUTTON_P2_UP          = 90;
    // Preview + Page right on the Zero
    public static final int     MKII_BUTTON_P2_DOWN        = 91;

    public static final int     MKI_BUTTON_TAP_TEMPO       = 94;
    public static final int     MKI_BUTTON_TAP_TEMPO_VALUE = 95;

    private static final int [] SL_BUTTONS_ALL             =
    {
        MKII_BUTTON_ROW1_1,
        MKII_BUTTON_ROW1_2,
        MKII_BUTTON_ROW1_3,
        MKII_BUTTON_ROW1_4,
        MKII_BUTTON_ROW1_5,
        MKII_BUTTON_ROW1_6,
        MKII_BUTTON_ROW1_7,
        MKII_BUTTON_ROW1_8,
        MKII_BUTTON_ROW2_1,
        MKII_BUTTON_ROW2_2,
        MKII_BUTTON_ROW2_3,
        MKII_BUTTON_ROW2_4,
        MKII_BUTTON_ROW2_5,
        MKII_BUTTON_ROW2_6,
        MKII_BUTTON_ROW2_7,
        MKII_BUTTON_ROW2_8,
        MKII_BUTTON_ROW3_1,
        MKII_BUTTON_ROW3_2,
        MKII_BUTTON_ROW3_3,
        MKII_BUTTON_ROW3_4,
        MKII_BUTTON_ROW3_5,
        MKII_BUTTON_ROW3_6,
        MKII_BUTTON_ROW3_7,
        MKII_BUTTON_ROW3_8,
        MKII_BUTTON_ROW4_1,
        MKII_BUTTON_ROW4_2,
        MKII_BUTTON_ROW4_3,
        MKII_BUTTON_ROW4_4,
        MKII_BUTTON_ROW4_5,
        MKII_BUTTON_ROW4_6,
        MKII_BUTTON_ROW4_7,
        MKII_BUTTON_ROW4_8,
        MKII_BUTTON_REWIND,
        MKII_BUTTON_FORWARD,
        MKII_BUTTON_STOP,
        MKII_BUTTON_PLAY,
        MKII_BUTTON_LOOP,
        MKII_BUTTON_RECORD,
        MKII_BUTTON_TRANSPORT,
        MKII_BUTTON_ROWSEL1,
        MKII_BUTTON_ROWSEL2,
        MKII_BUTTON_ROWSEL3,
        MKII_BUTTON_ROWSEL4,
        MKII_BUTTON_ROWSEL5,
        MKII_BUTTON_ROWSEL6,
        MKII_BUTTON_ROWSEL7,
        MKII_BUTTON_ROWSEL8,
        MKII_BUTTON_P1_UP,
        MKII_BUTTON_P1_DOWN,
        MKII_BUTTON_P2_UP,
        MKII_BUTTON_P2_DOWN
    };

    public static final int     MKII_BUTTON_STATE_OFF      = 0;
    public static final int     MKII_BUTTON_STATE_ON       = 1;

    public static final String  SYSEX_HEADER               = "F0 00 20 29 03 03 12 00 04 00 ";
    private static final String SYSEX_AUTOMAP_ON           = SYSEX_HEADER + "01 01 F7";
    private static final String SYSEX_AUTOMAP_OFF          = SYSEX_HEADER + "01 00 F7";

    private boolean             isMkII;
    private boolean             isTransportActive;
    private int                 lastCC94Value;


    /**
     * Constructor.
     *
     * @param host The host
     * @param colorManager The color manager
     * @param configuration The configuration
     * @param output The midi output
     * @param input The midi input
     * @param isMkII Is Pro or MkII?
     */
    public SLControlSurface (final IHost host, final ColorManager colorManager, final SLConfiguration configuration, final IMidiOutput output, final IMidiInput input, final boolean isMkII)
    {
        super (host, configuration, colorManager, output, input, new PadGridImpl (colorManager, output, 1, 8, 36)
        {
            /** {@inheritDoc} */
            @Override
            public void flush ()
            {
                // The drum pads do not have LEDs
            }
        }, SL_BUTTONS_ALL);

        this.isMkII = isMkII;

        // Necessary to store the value of CC94
        // since it comprises part of the temp value
        this.lastCC94Value = 0;

        this.display = new SLDisplay (host, output);

        // Switch to Ableton Automap mode
        this.output.sendSysex (SYSEX_AUTOMAP_ON);
        this.turnOffAllLEDs ();

        // Disable transport mode
        this.turnOffTransport ();

        // Set LED continuous mode
        for (int i = 0; i < 8; i++)
            this.output.sendCC (0x78 + i, 0);
    }


    /**
     * Returns true if it is the MkII otherwise MkI.
     *
     * @return True if it is the MkII otherwise MkI
     */
    public boolean isMkII ()
    {
        return this.isMkII;
    }


    /** {@inheritDoc} */
    @Override
    public void setButtonEx (int button, int channel, int value)
    {
        this.output.sendCCEx (channel, button, value);
    }


    /** {@inheritDoc} */
    @Override
    public void shutdown ()
    {
        this.display.clear ();
        this.turnOffAllLEDs ();
        this.output.sendSysex (SYSEX_AUTOMAP_OFF);

        super.shutdown ();
    }


    /** {@inheritDoc} */
    @Override
    public boolean isShiftPressed ()
    {
        return this.isTransportActive;
    }


    public void turnOffAllLEDs ()
    {
        this.output.sendCC (78, 127);
        for (final int button: this.getButtons ())
            this.setButton (button, 0);
    }


    public void turnOffTransport ()
    {
        this.isTransportActive = false;
        this.output.sendCC (MKII_BUTTON_TRANSPORT, 0);
    }


    public boolean isTransportActive ()
    {
        return this.isTransportActive;
    }


    public int getLastCC94Value ()
    {
        return this.lastCC94Value;
    }


    public void setLastCC94Value (final int lastCC94Value)
    {
        this.lastCC94Value = lastCC94Value;
    }
}