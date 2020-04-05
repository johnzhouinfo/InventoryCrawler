package view;

import model.BestBuy;
import model.PageNotFoundException;
import model.TheSource;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: Lingjue
 * @Date: 2020-04-05
 */

public class CrawlerController {
    private TheSource theSource;
    private BestBuy bestBuy;
    private boolean isStart = false;
    private int interval = 300;
    private Timer timer;
    private CrawlerView view;
    private Clip clip;

    public CrawlerController() {
        try {
            view = new CrawlerView(this);
            view.setVisible(true);
            theSource = new TheSource("https://www.thesource.ca/en-ca/gaming/nintendo-switch/nintendo-switch-consoles-bundles/nintendo-switch%e2%84%a2-animal-crossing-new-horizons-/p/108086956");
            bestBuy = new BestBuy("https://www.bestbuy.ca/en-ca/product/nintendo-switch-animal-crossing-new-horizons-edition/14425777");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resource/alert.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            view.setLog(String.format("[ERROR] %s, %s\n", new Date(), e.getMessage()));
        }

    }

    /**
     * Retrieve data and update view
     */
    private void updateView() {
        try {
            //The Source
            theSource.updateStatus();
            view.setTheSourceStatus(theSource.getStockStatus() ? "In stock" : "Out of stock");
            view.setLog(String.format("%s, Store: %s, Stock Status: %s, Price %s\n", new Date(), theSource.getShopName(), theSource.getStockStatus() ? "In stock" : "Out of stock", theSource.getProductPrice()));
            //BestBuy
            bestBuy.updateStatus();
            view.setBestbuyStatus(theSource.getStockStatus() ? "In stock" : "Out of stock");
            view.setLog(String.format("%s, Store: %s, Stock Status: %s, Price %s\n", new Date(), bestBuy.getShopName(), bestBuy.getStockStatus() ? "In stock" : "Out of stock", bestBuy.getProductPrice()));
            //If one of store restocked, play alert
            if (bestBuy.getStockStatus() || theSource.getStockStatus()){
                playSound();
            }
        }catch (PageNotFoundException e){
            view.setLog(String.format("[ERROR] %s, %s\n", new Date(), e.getMessage()));
        }

    }

    /**
     * Timer Controller
     */
    private void setInterval() {
        if (interval == 1) {
            interval = 300;
            updateView();
        }
        view.setTimer(interval + "s");
        --interval;
    }

    /**
     * Start to check process
     */
    public void start() {
        view.setLog(String.format("%s, Start\n", new Date()));
        view.setStatus("START");
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        updateView();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                setInterval();
            }
        }, delay, period);
    }

    /**
     * Stop process
     */
    public void stop() {
        interval = 300;
        view.setStatus("STOP");
        view.setLog(String.format("%s, Stop\n", new Date()));
        view.setTimer(interval + "s");
        timer.cancel();
    }

    /**
     * Play alert
     */
    public void playSound() {
        try {
            if (clip.isRunning()) {
                clip.stop();
            } else {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            view.setLog(String.format("[ERROR] %s, %s\n", new Date(), e.getMessage()));
        }

    }
}
