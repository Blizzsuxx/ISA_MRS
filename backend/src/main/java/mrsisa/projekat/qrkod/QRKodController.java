package mrsisa.projekat.qrkod;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import mrsisa.projekat.apoteka.ApotekaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path="api/qrkod")
public class QRKodController {
    private final QRKodService qrKodService;
    @Autowired
    public QRKodController(QRKodService qrKodService){
        this.qrKodService = qrKodService;
    }

    @PostMapping(value="/kreirajErecept")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public boolean formirajErecept(@RequestBody ApotekaDTO apotekaDTO){
        return this.qrKodService.kreirajErecept(apotekaDTO);
    }

    @PostMapping(value="/posaljiKod")
    @PreAuthorize("hasRole('ADMIN_SISTEMA')")
    public List<ApotekaDTO> dobaviApotekeErecepta(@RequestBody String tekst){
        String kod = tekst.substring(0, tekst.length()-1);
        String putanjaKoda = System.getProperty("user.dir") + "\\src\\main\\resources\\qrkod\\" + kod;
        System.out.println(putanjaKoda);

        String rezultat = "";
        try {
            BufferedImage bf = ImageIO.read(new FileInputStream(putanjaKoda));
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(bf)));
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println(result.getText());
            rezultat = result.getText();
        } catch(Exception e) {

        }
        return this.qrKodService.vratiApotekeNaOsnovuKoda(rezultat);
    }
}