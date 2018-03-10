package edu.core.gnr629.client;

import com.google.gwt.user.client.ui.DialogBox;
public class DialogBoxWithCloseButton extends DialogBox{

    /** The count. */
    private static int count = 0;

    /**
     * Define closeDialog using JSNI.
     * @param dialogBox The dialog box.
     * @param functionName The name of the function to invoke.
     */
    private static native void redefineClose(DialogBox dialogBox, String functionName)
    /*-{
          $wnd[functionName] = function()
          {
             dialogBox.@edu.core.gnr629.client.DialogBoxWithCloseButton::hideDialog()();
          }
     }-*/;

    /** The id. */
    private final String uid;

    /**
     * Creates an instance.
     */
    public DialogBoxWithCloseButton()
    {
        this(false);
    }

    /**
     * Creates an instance.
     * @param autoHide True to autohide.
     */
    public DialogBoxWithCloseButton(final boolean autoHide)
    {
        this(autoHide, true);
    }

    /**
     * Creates an instance.
     * @param autoHide True to autohide.
     * @param modal True to make the dialog modal.
     */
    public DialogBoxWithCloseButton(final boolean autoHide, final boolean modal)
    {
        super(autoHide, modal);
        setGlassEnabled(true);
        this.uid = "DialogBoxWithCloseButton_" + count++ + "_Close";
        setText("GetFeatureInfo");
    }

    /**
     * Hides the dialog.
     */
    public void hideDialog()
    {
        super.hide();
    }

    /*
     * (non-Javadoc)
     * @see com.google.gwt.user.client.ui.DialogBox#setHTML(java.lang.String)
     */
    @Override
    public void setHTML(final String html)
    {
        final String styleName = getStyleName() + "-closebutton";
        super.setHTML("<table border='0' cellspacing='0' cellpadding='0' width='100%'><tr><td width='5%'>&nbsp;</td><td align='left' width='90%'>" + html + "</td><td align='right' valign='middle' width='5%'><span class='" + styleName + "' onclick='"
                + this.uid + "()'>X</span></td></tr></table>");
        redefineClose(this, this.uid);
    }

    /*
     * (non-Javadoc)
     * @see com.google.gwt.user.client.ui.DialogBox#setText(java.lang.String)
     */
    @Override
    public void setText(final String text)
    {
        this.setHTML(text);
    }
}
