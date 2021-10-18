package ku.cs.shop.models;

import ku.cs.shop.services.PromotionSortByTimeComparator;

import java.util.*;

public class PromotionList {
    private ArrayList<Promotion> promotions;
    private Promotion currentPromotion;
    private Set<Promotion> codePromotion;

    public PromotionList(){
       promotions = new ArrayList<>();
       codePromotion = new HashSet<>();
    }

    public Promotion getCurrentPromotion() {
        return currentPromotion ;
    }

    public ArrayList<Promotion> getPromotions(){ return promotions; }

    public void addPromotion(Promotion promotion){
        promotions.add(promotion);
        sort();
    }

    // เช็กว่าโค้ดโปรโมชั่นซ้ำหรือไม่
    public boolean checkCodePromotionHaveUsed(String codePromotion) {
        for (Promotion promotion: this.promotions) {
            if (promotion.getCodePromotion().equals(codePromotion)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Promotion> getPromotionByShopName(String shopName) {
        ArrayList<Promotion> promotionByShopName = new ArrayList<>();
        for(Promotion promotion : promotions){
            if(promotion.getShopName().equals(shopName)){
                promotionByShopName.add(promotion);
            }
        }
        return promotionByShopName;
    }

    public ArrayList<Promotion> checkPromotionByShopNameAndCode(String shopName, String codePromotion) {
        ArrayList<Promotion> promotionByShopName = new ArrayList<>();
        for(Promotion promotion : promotions){
            if(promotion.getShopName().equals(shopName) && promotion.getCodePromotion().equals(codePromotion)){
                promotionByShopName.add(promotion);
            }
        }
        return promotionByShopName;
    }

    public Promotion code(String shopName, String codePromotion) {
        for (Promotion promotion : this.promotions) {
            if (promotion.getShopName().equals(shopName)) {
                if (promotion.getCodePromotion().equals(codePromotion)) {
                    currentPromotion = promotion ;
                    return promotion ;
                }
            }
        }
        return null ;
    }

    public int getCountPromotionByShopName (String shopName) {
        int count = 0;
        ArrayList<Promotion> countPromotionByShopName = new ArrayList<>();
        for(Promotion promotion : promotions){
            if(promotion.getShopName().equals(shopName)){
                countPromotionByShopName.add(promotion);
                count++;
            }
        }
        return count;
    }

    public void sort(Comparator<Promotion> promotionComparator) {
        Collections.sort(this.promotions, promotionComparator);
    }

    public void sort() {
        PromotionSortByTimeComparator promotionSortByTimeComparator = new PromotionSortByTimeComparator();
        sort(promotionSortByTimeComparator);
    }

    public String toCsv() {
        String result = "" ;
        for (Promotion promotion : this.promotions) {
            result += promotion.toCsv() + "\n";
        }
        return result ;
    }
}
