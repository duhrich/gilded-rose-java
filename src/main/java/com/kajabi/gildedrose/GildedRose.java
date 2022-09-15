package com.kajabi.gildedrose;

public class GildedRose {
    public final String aged = "Aged Brie";
    public final String passes = "Backstage passes to a TAFKAL80ETC concert";
    public final String legendary = "Legendary Sulfuras, Hand of Ragnaros";

    public final String conjured = "Conjured Mana Cake";
    private String name;
    private int daysRemaining;
    private int quality;

    public GildedRose(String name, int daysRemaining, int quality) {
        this.name = name;
        this.daysRemaining = daysRemaining;
        this.quality = quality;
    }

    public void tick() {
        decreaseDaysRemaining();
        handleNormalAging();
        if (isExpired()) {
            handleExpiredGoods();
        }
    }

    private boolean isExpired() {
        return this.daysRemaining < 0;
    }

    private void handleNormalAging() {
        if (isAged() || isPass()) {
            safelyIncreaseQuality();
            if (isPass()) {
                handleAcceleratedAging();
            }
        } else {
            safelyDecreaseQuality();
        }
    }

    private boolean isAged() {
        return aged.equals(this.name);
    }

    private boolean isPass() {
        return passes.equals(this.name);
    }

    private void handleAcceleratedAging() {
        if (this.daysRemaining < 10) {
            safelyIncreaseQuality();
        }
        if (this.daysRemaining < 5) {
            safelyIncreaseQuality();
        }
    }

    private void handleExpiredGoods() {
        if (isAged()) {
            safelyIncreaseQuality();
        } else {
            if (isPass()) {
                this.quality = 0;
            } else {
                safelyDecreaseQuality();
            }
        }
    }

    private void decreaseDaysRemaining() {
        if (!isLegendary()) {
            this.daysRemaining--;
        }
    }

    private boolean isLegendary() {
        return legendary.equals(this.name);
    }

    private void safelyIncreaseQuality() {
        if (this.quality < 50) {
            this.quality++;
        }
    }

    private void safelyDecreaseQuality() {
        if (this.quality > 0) {
            if (!this.name.equals(legendary)) {
                this.quality--;
                if (this.quality > 0 && shouldDoubleDecrease()) {
                    this.quality--;
                }
            }
        }
    }

    private boolean shouldDoubleDecrease() {
        return conjured.equals(this.name);
    }

    public String getName() {
        return name;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public int getQuality() {
        return quality;
    }


}