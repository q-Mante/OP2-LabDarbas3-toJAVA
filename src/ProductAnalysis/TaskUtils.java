package ProductAnalysis;

public class TaskUtils {
    public static LinkList<GeneralProductInfo> findFavorites(LinkList<Shop> linkedShops) {
        LinkList<GeneralProductInfo> favorites = new LinkList<GeneralProductInfo>();
        for (Shop shop : linkedShops) {
            GeneralProductInfo favorite = shop.favoriteProduct().getProductInfo();
            if (!favorites.Contains(favorite)) {
                favorites.Add(favorite);
            }
        }
        return favorites;
    }

    public static LinkList<ShopProductInfo> findProductsThatExpireIn(LinkList<Shop> linkedShops, int days) {
        LinkList<ShopProductInfo> expires = new LinkList<ShopProductInfo>();
        for (Shop shop : linkedShops) {
            shop.findExpires(expires, days);
        }
        return expires;
    }

    public static Shop findShopWithBiggestAssortment(LinkList<Shop> linkedShops) {
        Shop biggest = new Shop();
        int count = -1;

        for (Shop shop : linkedShops) {
            if (shop.productsCount() > count) {
                biggest = shop;
                count = shop.productsCount();
            }
        }
        return biggest;
    }

    public static LinkList<Shop> findShopsThatAreBelowSpecifiedValue(LinkList<Shop> linkedShops, float maximumValue) {
        LinkList<Shop> shops = new LinkList<Shop>();
        for (Shop shop : linkedShops) {
            if (shop.getValue() <= maximumValue) {
                shops.Add(shop);
            }
        }
        return shops;
    }
}
