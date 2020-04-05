package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author: Lingjue
 * @Date: 2020-04-05
 */
public class TheSource {
    private final String SHOP_NAME = "The Source";
    private String PRODUCT_NAME;
    private String  PRODUCT_PRICE;
    private boolean STOCK_STATUS;
    private String PRODUCT_URL;

    public TheSource(String URL) {
        PRODUCT_URL = URL;
    }

    public void updateStatus() {
        try {
            Document doc = Jsoup.connect(PRODUCT_URL)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0")
                    .get();
            Elements product = doc.select(".productDescription");
            Elements btn = product.get(0).getElementsByClass("#addToCartForm button primary-button full-button addToCartButton");
            Element productName = product.get(0).selectFirst(".pdp-name span");
            Element listPrice = product.get(0).selectFirst("#addToCartForm [name = price]");

            PRODUCT_NAME = productName.html();
            PRODUCT_PRICE = listPrice.attr("value");
            STOCK_STATUS = btn.size() != 0;
        }catch (IOException e){
            throw new PageNotFoundException("The Source product doesn't exist!");
        }

    }

    public boolean getStockStatus() {
        return STOCK_STATUS;
    }

    public String  getProductPrice() {
        return PRODUCT_PRICE;
    }

    public String getProductName() {
        return PRODUCT_NAME;
    }

    public String getProductURL() {
        return PRODUCT_URL;
    }

    public String getShopName() {
        return SHOP_NAME;
    }
}

