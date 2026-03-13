package vod.repository.mem;

import vod.model.Bakery;
import vod.model.Baker;
import vod.model.Product;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<Bakery> bakeries = new ArrayList<>();

    static List<Baker> bakers = new ArrayList<>();

    static List<Product> products = new ArrayList<>();

    static {
        Baker kowalski = new Baker(1, "Wojciech", "Kowalski");
        Product chlebBanan = new Product(1, "Chleb Bananowy", "Chleb", kowalski, (float) 4.5);

        Bakery podStolem = new Bakery(1, "Piekarnia - Pod Stolem", "logo.png");
        bind(podStolem, chlebBanan);
        bind(chlebBanan, kowalski);
        products.add(chlebBanan);
        bakers.add(kowalski);
        bakeries.add(podStolem);

        Baker kowalski2 = new Baker(2, "Adam", "Kowalski");
        Product chlebZwykly = new Product(2, "Chleb Zwykly", "Chleb", kowalski, (float) 3);

        Bakery nowa = new Bakery(2, "Piekarnia - Nova", "logo.png");
        bind(nowa, chlebZwykly);
        bind(nowa, chlebBanan);
        bind(chlebZwykly, kowalski2);
        products.add(chlebZwykly);
        bakers.add(kowalski2);
        bakeries.add(nowa);
       /*
        Baker smarzowski = new Baker(1, "Wojciech", "Smarzowski");
        Baker vega = new Baker(2, "Patryk", "Vega");
        Baker wajda = new Baker(3, "Andrzej", "Wajda");
        Baker skolimowski = new Baker(4, "Jerzy", "Skolimowski");

        Product wolyn = new Product(1, "Wolyn", "https://ocdn.eu/pulscms-transforms/1/D0gk9kuTURBXy8zYzFhMDRhZS1jOGRiLTQxN2YtOTcwYy1iNjRjZDBkMjc4MDYuanBlZ5GTBc0DFM0BvIGhMAU", smarzowski, (float) 4.1);
        Product wesele = new Product(2, "Wesele", "https://fwcdn.pl/fpo/40/98/124098/7521214.6.jpg", smarzowski, (float) 4.3);

        Product polityka = new Product(3, "Polityka", "https://i.iplsc.com/-/00094J03E94SMPSS-C122.jpg", vega, (float) 3.9);
        Product pitbul = new Product(4, "Pitbul", "https://bi.im-g.pl/im/5b/9b/12/z19510363V,-Pitbull--Nowe-porzadki---rez--Patryk-Vega--plakat.jpg", vega, (float) 3.1);

        Product katyn = new Product(5, "Katyn", "http://www.gokmichalowo.pl/imprezy2007/katyn/plakat_maly.jpg", wajda, (float) 4.7);
        Product tatarak = new Product(6, "Tatarak", "http://gapla.fn.org.pl/public/cache/P21829-483x700.jpg", wajda, (float) 4.4);

        Product essential = new Product(7, "Essential killing", "https://m.media-amazon.com/images/M/MV5BNTE5NjAxMTEzNl5BMl5BanBnXkFtZTcwMjYzMDQ0Ng@@._V1_UX182_CR0,0,182,268_AL_.jpg", skolimowski, (float) 4.9);
        Product ferdydurke = new Product(8, "Ferdydurke", "http://gapla.fn.org.pl/public/cache/P19423-483x700.jpg", skolimowski, (float) 4.3);

        bind(wolyn, smarzowski);
        bind(wesele, smarzowski);

        bind(polityka, vega);
        bind(pitbul, vega);

        bind(katyn, wajda);
        bind(tatarak, wajda);

        bind(essential, skolimowski);
        bind(ferdydurke, skolimowski);

        Bakery kinoteka = new Bakery(1, "Kinoteka", "https://www.kinoteka.pl/img/logo.png");
        Bakery podBaranami = new Bakery(2, "Kino pod Baranami", "http://www.festiwalfilmuniemego.pl/wp-content/uploads/2015/11/Kino-pod-Baranami.png");
        Bakery noweHoryzonty = new Bakery(3, "Kino Nowe Horyzonty", "https://i2.wp.com/garretreza.pl/wp-content/uploads/2018/07/nh.jpg");
        Bakery zak = new Bakery(4, "Kino Zak", "https://static2.s-trojmiasto.pl/zdj/c/n/19/2276/250x0/2276445.jpg");

        bind(kinoteka, wesele);
        bind(noweHoryzonty, wesele);
        bind(noweHoryzonty, wolyn);
        bind(noweHoryzonty, polityka);

        bind(kinoteka, tatarak);
        bind(zak, tatarak);
        bind(zak, essential);
        bind(podBaranami, essential);
        bind(podBaranami, polityka);

        products.add(wolyn);
        products.add(wesele);
        products.add(polityka);
        products.add(pitbul);
        products.add(katyn);
        products.add(tatarak);
        products.add(essential);
        products.add(ferdydurke);

        bakers.add(smarzowski);
        bakers.add(vega);
        bakers.add(wajda);
        bakers.add(skolimowski);

        bakeries.add(kinoteka);
        bakeries.add(podBaranami);
        bakeries.add(noweHoryzonty);
        bakeries.add(zak);
    */
    }

    private static void bind(Bakery c, Product m) {
        c.addProduct(m);
        m.addBakeries(c);
    }

    private static void bind(Product m, Baker d) {
        d.addMovie(m);
        m.setBaker(d);
    }

}
