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

    public String getName() {
        return name;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public int getQuality() {
        return quality;
    }

    public void tick() {
        if (aged.equals(this.name) || passes.equals(this.name)) {
             if (this.quality < 50) {
                this.quality++;
                if (this.name.equals(passes)) {
                    if (this.daysRemaining < 11) {
                        increaseQuality();
                    }
                    if (this.daysRemaining < 6) {
                        increaseQuality();
                    }
                }
            }
        } else {
            safelyDecreaseQuality(this.name);
        }
        decreaseDaysRemaining();
        if (this.daysRemaining < 0) {
            if (!this.name.equals(aged)) {
                if (!this.name.equals(passes)) {
                    safelyDecreaseQuality(this.name);
                } else {
                    this.quality = 0;
                }
            } else {
                increaseQuality();
            }
        }
    }

    private void decreaseDaysRemaining() {
        if (!this.name.equals(legendary)) {
            this.daysRemaining--;
        }
    }

    private void increaseQuality() {
        if (this.quality < 50) {
            this.quality++;
        }
    }

    private void safelyDecreaseQuality(String name) {
        if (this.quality > 0) {
            if (!this.name.equals(legendary)) {
                this.quality--;
                if (this.quality > 0 && shouldDoubleDecrease(name)) {
                    this.quality--;
                }
            }
        }
    }

    private boolean shouldDoubleDecrease(String name) {
        return name.equals(conjured);
    }
}