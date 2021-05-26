<template>
  <el-main>
    <NavMeniZaPacijenta />
    <div id="map" class="map">
      <div id="popup" class="ol-popup" ref="container">
        <a href="#" id="popup-closer" class="ol-popup-closer" ref="closer"></a>
        <div id="popup-content" ref="content"></div>
      </div>
    </div>
    <APPostojeceApoteke />
  </el-main>
</template>
<style scoped>

      .map {
        width: 100%;
        height:400px;
      }
      .ol-popup {
        position: absolute;
        background-color: white;
        box-shadow: 0 1px 4px rgba(0,0,0,0.2);
        padding: 15px;
        border-radius: 10px;
        border: 1px solid #cccccc;
        bottom: 12px;
        left: -50px;
        min-width: 280px;
      }
      .ol-popup:after, .ol-popup:before {
        top: 100%;
        border: solid transparent;
        content: " ";
        height: 0;
        width: 0;
        position: absolute;
        pointer-events: none;
      }
      .ol-popup:after {
        border-top-color: white;
        border-width: 10px;
        left: 48px;
        margin-left: -10px;
      }
      .ol-popup:before {
        border-top-color: #cccccc;
        border-width: 11px;
        left: 48px;
        margin-left: -11px;
      }
      .ol-popup-closer {
        text-decoration: none;
        position: absolute;
        top: 2px;
        right: 8px;
      }
      .ol-popup-closer:after {
        content: "✖";
      }
   
</style>
<script>
import APPostojeceApoteke from "./APPostojeceApoteke";
import NavMeniZaPacijenta from "./NavMeniZaPacijenta";
import * as olInteraction from "ol/interaction";
import Map from "ol/Map";
import TileLayer from "ol/layer/Tile";
import View from "ol/View";
import OSM from "ol/source/OSM";
import "ol/ol.css";
import * as condition from "ol/events/condition";
import { fromLonLat } from "ol/proj";
import Overlay from "ol/Overlay";
import VectorLayer from "ol/layer/Vector";
import Feature from "ol/Feature";
import Point from "ol/geom/Point";
import VectorSource from 'ol/source/Vector';
export default {
  components: { APPostojeceApoteke, NavMeniZaPacijenta },
  name: "IzmenaLicnihInfoKorisnika",
  data() {
    return {
      map: null,
    };
  },
  async mounted() {
    this.map = new Map({
      target: "map",
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      view: new View({
        center: fromLonLat([19.83, 45.26]),
        zoom: 13,
      }),
    });
    let layer = null;
    let container = this.$refs["container"];
    let content = this.$refs["content"];
    let closer = this.$refs["closer"];
    let overlay = new Overlay({
      element: container,
      autoPan: true,
      autoPanAnimation: {
        duration: 250,
      },
    });
    let selectClick = new olInteraction.Select({
      condition: condition.pointerMove,
    });
    this.map.addInteraction(selectClick);
    this.map.addOverlay(overlay);

    closer.onclick = function () {
      overlay.setPosition(undefined);
      closer.blur();
      return false;
    };

    selectClick.on("select", function (evt) {
      try {
        let featureSelected = evt.selected[0];
        let layer = selectClick.getLayer(featureSelected);
        let ime = layer.get("ime");
        let ulica = layer.get("ulica")
        let broj = layer.get("broj")
        if (ime === undefined) return;
        content.innerHTML = `<p><b>${ime}</b></p>`;
        content.innerHTML += `<p>Ulica:${ulica}</p>`;
        content.innerHTML += `<p>Broj:${broj}</p>`;
        overlay.setPosition(evt.mapBrowserEvent.coordinate);
      } catch (Exception) {
        return;
      }
    });
    let apoteke = []
    this.$store.dispatch("APApoteke/dobaviApoteke").then(()=>{
      apoteke = this.$store.state.APApoteke.sveApoteke;
      apoteke.forEach((apoteka) => {
      layer = new VectorLayer({
        ime: apoteka.ime,
        ulica: apoteka.ulica,
        broj : apoteka.broj,
        
        source: new VectorSource({
          features: [
            new Feature({
              geometry: new Point(
                fromLonLat([apoteka.duzina,apoteka.sirina])
              ),
            }),
          ],
        }),
      });

      this.map.addLayer(layer);
    });
    });
   
   
    
  },
};
</script>