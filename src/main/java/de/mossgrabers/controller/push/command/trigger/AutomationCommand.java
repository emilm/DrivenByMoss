// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.controller.push.command.trigger;

import de.mossgrabers.controller.push.PushConfiguration;
import de.mossgrabers.controller.push.controller.PushControlSurface;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;
import de.mossgrabers.framework.daw.IModel;
import de.mossgrabers.framework.mode.Modes;
import de.mossgrabers.framework.utils.ButtonEvent;


/**
 * Command to enable automation and edit its settings.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class AutomationCommand extends AbstractTriggerCommand<PushControlSurface, PushConfiguration>
{
    private boolean quitAutomationMode;


    /**
     * Constructor.
     *
     * @param model The model
     * @param surface The surface
     */
    public AutomationCommand (final IModel model, final PushControlSurface surface)
    {
        super (model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final ButtonEvent event)
    {
        if (this.surface.isDeletePressed ())
        {
            this.surface.setButtonConsumed (this.surface.getDeleteButtonId ());
            if (event == ButtonEvent.DOWN)
                this.model.getTransport ().resetAutomationOverrides ();
            return;
        }

        switch (event)
        {
            case DOWN:
                this.quitAutomationMode = false;
                break;
            case LONG:
                this.quitAutomationMode = true;
                this.surface.getModeManager ().setActiveMode (Modes.MODE_AUTOMATION);
                break;
            case UP:
                if (this.quitAutomationMode)
                    this.surface.getModeManager ().restoreMode ();
                else
                    this.doCommand ();
                break;
        }
    }


    private void doCommand ()
    {
        final boolean isShift = this.surface.isShiftPressed ();
        final boolean flipRecord = this.surface.getConfiguration ().isFlipRecord ();
        if (isShift && !flipRecord || !isShift && flipRecord)
            this.model.getTransport ().toggleWriteClipLauncherAutomation ();
        else
            this.model.getTransport ().toggleWriteArrangerAutomation ();
    }
}
