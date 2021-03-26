//package mrsisa.projekat.apoteka;
//
//import mrsisa.projekat.lijek.Lijek;
//import mrsisa.projekat.stanjelijeka.StanjeLijeka;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class ApotekaConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(ApotekaRepository repozitorijum){
//        return args ->{
//            Apoteka apoteka =  new Apoteka();
//            apoteka.setId(1L);
//            apoteka.setIme("Prva apoteka");
//            List<StanjeLijeka> stanjaLijekova = List.of(
//                    new StanjeLijeka(
//                            1L,
//                            new Lijek(
//                                    1L,
//                                    "Paracetamol",
//                                    "Protiv boli",
//                                    "tableta",
//                                    "ljiek",
//                                    "Biofarm",
//                                    "Lijek"
//                            ),
//                            10,
//                            false
//                    ),
//                    new StanjeLijeka(
//                            2L,
//                            new Lijek(
//                                    2L,
//                                    "Brufen",
//                                    "Protiv boli",
//                                    "tableta",
//                                    "ljiek",
//                                    "Biofarm",
//                                    "Lijek"
//                            ),
//                            20,
//                            false
//
//                    ),
//                    new StanjeLijeka(
//
//                            3L,
//                            new Lijek(
//                                    3L,
//                                    "Lekadol",
//                                    "Protiv boli",
//                                    "tableta",
//                                    "ljiek",
//                                    "Biofarm",
//                                    "Lijek"
//                            ),
//                            15,
//                            true
//                    )
//            );
//            apoteka.setLijekovi(stanjaLijekova);
//            repozitorijum.save(apoteka);
//        };
//    }
//}
