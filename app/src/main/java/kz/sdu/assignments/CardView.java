package kz.sdu.assignments;

import android.content.Context;

public class CardView extends androidx.appcompat.widget.AppCompatImageView {
    private int URI, order;
    private boolean isVisible = false;

    public CardView(Context context, int URI, int order) {
        super(context);
        this.URI = URI;
        this.order = order;
        setBackgroundResource(R.drawable.card_back_blue1);
    }

    public boolean showCard(int order) {
        setBackgroundResource(this.URI);
        this.isVisible = true;
        return this.order == order;
    }

    public void hideCard() {
        this.isVisible = false;
        setBackgroundResource(R.drawable.card_back_blue1);
    }

    public int getURI() {
        return this.URI;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public int getOrder() {
        return order;
    }
}
