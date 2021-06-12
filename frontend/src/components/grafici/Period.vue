<template>
  <div>
    <el-row>
      <el-col
        ><el-date-picker v-model="value1" type="date" placeholder="Datum od">
        </el-date-picker>
        <el-date-picker v-model="value2" type="date" placeholder="Datum do">
        </el-date-picker>
        <el-button @click="pronadjiPodatke" type="primary" plain>Pronadji</el-button>
      </el-col>
    </el-row>
    <canvas
      
      ref="prihodiPeriod"
      style="position: relative; height: 40vh; width: 80vw"
    ></canvas>
  </div>
</template>

<script>
import Chart from "chart.js";

export default {
  name: "PlanetChart",
  data() {
    return {
      labele: new Array(),
      podaci: new Array(),
      pregledi: new Array(),
      kvartali: new Array(),
      boje: new Array(),
      godineLabele: new Array(),
      godinePodaci: new Array(),
      ucitano: false,
      value1: '',
      value2: '',
    };
  },
  methods: {

    pronadjiPodatke(){
            this.$store.dispatch("APApoteke/izvjestajPeriod",{datumOd:this.value1,datumDo:this.value2}).then((podaci)=>{
                this.podaci = podaci;
                this.iscrtaj(podaci);
      })
    },
    iscrtaj(podaci) {
      console.log(podaci);
      for (const dio in podaci.prihodiPeriod) {
        this.godineLabele.push(dio);
        this.godinePodaci.push(podaci.prihodiPeriod[dio].toFixed(2));
        
      }
      this.inicijalizujBar();
    },
    inicijalizujBar() {
    this.ucitano = true;
      new Chart(this.$refs.prihodiPeriod, {
        type: "line",
        data: {
          labels: this.godineLabele,
          datasets: [
            {
              data: this.godinePodaci,
              label: "Prihodi po danima",
              borderColor: "#3e95cd",
              fill: false,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Prihodi po danima",
          },
        },
      });
      
    },
  },
  mounted() {},
};
</script>