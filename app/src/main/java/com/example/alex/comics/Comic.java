package com.example.alex.comics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

abstract class Comic {
    String link;
    URLConnection con = null;
    BufferedReader br = null;
    InputStreamReader isr = null;


    Comic(String link) {
        this.link = link;
    }

    void init() {
        try {
            this.con = new URL(this.link).openConnection();
            this.isr = new InputStreamReader(this.con.getInputStream());
            this.br = new BufferedReader(this.isr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void clean() {
        if (this.br != null) {
            try {
                this.br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // needs to be implemented by each comic site class
    public abstract String getDirectComicLink();
}

class XKCD extends Comic {
    public XKCD() {
        super("https://c.xkcd.com/random/comic/");
    }

    @Override
    public String getDirectComicLink() {
        init();

        String line;

        try {
            while ((line = this.br.readLine()) != null) {
                int i = line.indexOf("https://imgs.xkcd.com");

                if (i >= 0)
                    return line.substring(i, line.length());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        clean();

        return null;
    }
}