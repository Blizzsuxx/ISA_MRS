package mrsisa.projekat.qrkod;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/qrkod")
public class QRKodController {
    private final QRKodService qrKodService;
    @Autowired
    public QRKodController(QRKodService qrKodService){
        this.qrKodService = qrKodService;
    }

    @PostMapping(value="/kreirajErecept")
    @PreAuthorize("hasRole('PACIJENT')")
    public boolean formirajErecept(@RequestBody ApotekaDTO apotekaDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Pacijent p = (Pacijent) auth.getPrincipal();
        return this.qrKodService.kreirajErecept(apotekaDTO, p);
    }

    @PostMapping(value="/posaljiKod")
    @PreAuthorize("hasRole('PACIJENT')")
    public List<ApotekaDTO> dobaviApotekeErecepta(@RequestBody String tekst){
        String kod = tekst.substring(0, tekst.length()-1);
        String putanjaKoda = System.getProperty("user.dir") + "\\src\\main\\resources\\qrkod\\" + kod;

        String rezultat = "";
        try {
            BufferedImage bf = ImageIO.read(new FileInputStream(putanjaKoda));
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(bf)));
            Result result = new MultiFormatReader().decode(bitmap);
            rezultat = result.getText();

            String[] tokeni = rezultat.split("\\|");
            if (tokeni.length != 3) // da provjerimo je li dovoljno tokena izparsirano
                return null;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Pacijent p = (Pacijent) auth.getPrincipal();
            if (!p.getUsername().equals(tokeni[1]))  // ako ga koristi pacijent koji ne bi trebao
                return null;
            return this.qrKodService.vratiApotekeNaOsnovuKoda(tokeni);
        } catch(Exception e) {
            return null;
        }
    }
}
