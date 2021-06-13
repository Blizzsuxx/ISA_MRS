<template>
  <div>
    <canvas ref="preglediMjeseci"  style="position: relative; height:40vh; width:80vw"></canvas>
    <canvas ref="preglediMjeseciKvartali"  style="position: relative; height:40vh; width:80vw"></canvas>
    <canvas ref="pregeldiGodine"  style="position: relative; height:40vh; width:80vw"></canvas>
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
      godineLabele : new Array(),
      godinePodaci: new Array(),
    }
  },
  methods: {
    iscrtaj(podaci) {
      console.log(podaci)
      this.pregledi = podaci.preglediPoMjesecima;
      let i = 0;
      let sum = 0;
      this.pregledi.forEach(item=>{
          sum=sum+item;
          i++;
          if(i===3){
            this.kvartali.push(sum);
            i=0;
            sum = 0;
          }
      })

      for (const dio in podaci.preglediGodine) {
        this.godineLabele.push(dio);
        this.godinePodaci.push(podaci.preglediGodine[dio].toFixed(2));
        this.boje.push("#3cba9f");
        
      }
      this.inicijalizujBar();
    },
    inicijalizujBar() {
      new Chart(this.$refs.preglediMjeseci, {
        type: "line",
        data: {
          labels: ["Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust", "Septembar", "Oktobar","Novembar","Decembar"],
          datasets: [
            {
              data:this.pregledi,
              label: "Pregledi",
              borderColor: "#3e95cd",
              fill: false,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Pregledi po mjesecima",
          },
        },
      });
      new Chart(this.$refs.preglediMjeseciKvartali, {
        type: "line",
        data: {
          labels: ["Prvi kvartal", "Drugi kvartal", "Treci kvartal", "Cetvrti kvartal"],
          datasets: [
            {
              data:this.kvartali,
              label: "Pregledi",
              borderColor: "#3e95cd",
              fill: false,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Pregledi po kvartalima",
          },
        },
      });
      new Chart(this.$refs.pregeldiGodine, {
        type: "bar",
        data: {
          labels:this.godineLabele,
          datasets: [
            {
              data:this.godinePodaci,
              label: "Pregledi",
              backgroundColor: this.boje,
              fill: false,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Pregledi po godinama",
          },
        },
      });
    },

    




    
  },
  mounted() {},
};
</script>