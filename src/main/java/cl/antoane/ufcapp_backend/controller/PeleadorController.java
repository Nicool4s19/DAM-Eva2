package cl.antoane.ufcapp_backend.controller;

import cl.antoane.ufcapp_backend.model.PeleadorModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/peleadores")
public class PeleadorController {

    @GetMapping("/top5")
    public List<PeleadorModel> obtenerTop5() {

        PeleadorModel p1 = new PeleadorModel(
                1L, "Islam Makhachev",
                28, 1, 0,
                "Welter", 1,
                "https://ufc.com/images/styles/athlete_profile_listing_medium_1x/s3/2025-01/7/MAKHACHEV_ISLAM_BELT_01-18.png?itok=6b_rZU3G"
        );

        PeleadorModel p2 = new PeleadorModel(
                2L, "Alex Pereira",
                13, 3, 0,
                "Semipesado", 1,
                "https://ufc.com/images/styles/athlete_profile_listing_medium_1x/s3/2025-03/PEREIRA_ALEX_BELT_03-08.png?itok=e10RWmGb"
        );

        PeleadorModel p3 = new PeleadorModel(
                3L, "Ilia Topuria",
                17, 0, 0,
                "Ligero", 1,
                "https://ufc.com/images/styles/athlete_profile_listing_medium_1x/s3/2025-06/TOPURIA_ILIA_BELT_10-26.png?itok=rSOn_juG"
        );

        PeleadorModel p4 = new PeleadorModel(
                4L, "Petr Yan",
                20, 5, 0,
                "Gallo", 1,
                "https://ufc.com/images/styles/athlete_profile_listing_medium_1x/s3/2025-12/YAN_PETR_BELT.png?itok=I3MTCfoO"
        );

        PeleadorModel p5 = new PeleadorModel(
                5L, "Tom Aspinall",
                15, 3, 0,
                "Pesado", 1,
                "https://ufc.com/images/styles/athlete_profile_listing_medium_1x/s3/2025-10/ASPINALL_TOM_BELT_10-25.png?itok=vRWn6gjQ"
        );

        return Arrays.asList(p1, p2, p3, p4, p5);
    }
}
