package com.johnlindquist.acejump.ui;

import com.intellij.openapi.util.Pair;
import com.johnlindquist.acejump.AceJumpAction;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 8/10/12
 * Time: 9:45 AM
 */
public class AceCanvas extends JComponent {
    private List<Pair<String, Point>> jumpInfos;
    private Pair<Color, Color> colorPair;
    private float lineSpacing;
    private int lineHeight;


    public void setJumpInfos(@Nullable List<Pair<String, Point>> ballonInfos) {
        this.jumpInfos = ballonInfos;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
        if (jumpInfos == null) return;

        Font font = getFont();
        Graphics2D g2d = (Graphics2D) g;
        int fontWidth = getFontMetrics(font).stringWidth("w");
        int fontHeight = font.getSize();

        int rectMarginWidth = fontWidth / 2;
        int doubleRectMarginWidth = rectMarginWidth * 2;
        int rectHOffset = (int) (fontHeight * lineSpacing - fontHeight);
        int rectWidth = fontWidth + doubleRectMarginWidth;

        float hOffset = font.getSize() - (font.getSize() * lineSpacing);


        for (Pair<String, Point> ballonInfo : jumpInfos) {

            String text = ballonInfo.getFirst();
            Point originalPoint = ballonInfo.getSecond();
            Color defaultForeground = colorPair.getSecond();
            Color defaultBackground = colorPair.getFirst();

            originalPoint.translate(0, (int) -hOffset);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //The rect/text are painted as the opposite colors of your scheme settings for font/background

            //a slight border for "pop" against the background
            g2d.setColor(defaultBackground);
            g2d.drawRect(originalPoint.x - rectMarginWidth - 1, originalPoint.y - rectHOffset - 1, rectWidth + 1, lineHeight + 1);

            //the background rectangle
            g2d.setColor(defaultForeground);
            g2d.fillRect(originalPoint.x - rectMarginWidth, originalPoint.y - rectHOffset, rectWidth, lineHeight);

            //just a touch of alpha
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));

            //the foreground text
            g2d.setFont(font);
            g2d.setColor(defaultBackground);
            g2d.drawString(text.toUpperCase(), originalPoint.x, originalPoint.y + fontHeight);

        }

    }

    public void setBackgroundForegroundColors(Pair<Color, Color> colorPair) {
        this.colorPair = colorPair;
    }


    public void setLineSpacing(float lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }


    public void clear() {
        setJumpInfos(null);
        repaint();
    }
}
