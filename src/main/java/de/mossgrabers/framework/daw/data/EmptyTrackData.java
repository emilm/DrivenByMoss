// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.daw.data;

import de.mossgrabers.framework.daw.EmptyBank;
import de.mossgrabers.framework.daw.ISlotBank;
import de.mossgrabers.framework.observer.NoteObserver;


/**
 * Default data for an empty track.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class EmptyTrackData extends EmptyChannelData implements ITrack
{
    /** The singleton. */
    public static final ITrack INSTANCE = new EmptyTrackData ();

    private final ISlotBank    slotBank = new EmptySlotBank ();


    /** {@inheritDoc} */
    @Override
    public boolean isGroup ()
    {
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public boolean isRecArm ()
    {
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public boolean isMonitor ()
    {
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public boolean isAutoMonitor ()
    {
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public boolean canHoldNotes ()
    {
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public boolean canHoldAudioData ()
    {
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public String getCrossfadeMode ()
    {
        return "AB";
    }


    /** {@inheritDoc} */
    @Override
    public boolean isPlaying ()
    {
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public void setRecArm (final boolean value)
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void toggleRecArm ()
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void setMonitor (final boolean value)
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void toggleMonitor ()
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void setAutoMonitor (final boolean value)
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void toggleAutoMonitor ()
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void toggleNoteRepeat ()
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void setNoteRepeatLength (final double length)
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public double getNoteRepeatLength ()
    {
        return 1.0;
    }


    /** {@inheritDoc} */
    @Override
    public boolean isNoteRepeat ()
    {
        // Intentionally empty
        return false;
    }


    /** {@inheritDoc} */
    @Override
    public void changeCrossfadeModeAsNumber (final int control)
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void setCrossfadeMode (final String mode)
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public int getCrossfadeModeAsNumber ()
    {
        // Intentionally empty
        return 0;
    }


    /** {@inheritDoc} */
    @Override
    public void setCrossfadeModeAsNumber (final int modeValue)
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void toggleCrossfadeMode ()
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void stop ()
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public void returnToArrangement ()
    {
        // Intentionally empty
    }


    /** {@inheritDoc} */
    @Override
    public ISlotBank getSlotBank ()
    {
        // Intentionally empty
        return this.slotBank;
    }


    /** {@inheritDoc} */
    @Override
    public void addNoteObserver (final NoteObserver observer)
    {
        // Intentionally empty
    }

    class EmptySlotBank extends EmptyBank<ISlot> implements ISlotBank
    {
        @Override
        public ISlot getEmptySlot (final int startFrom)
        {
            return null;
        }
    }
}
