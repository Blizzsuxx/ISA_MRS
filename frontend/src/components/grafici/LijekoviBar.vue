<template>
  <div>
    <canvas ref="lijekoviMjeseci"  style="position: relative; height:40vh; width:80vw"></canvas>
    <canvas ref="lijekoviMjeseciKvartali"  style="position: relative; height:40vh; width:80vw"></canvas>
    <canvas ref="lijekoviGodine"  style="position: relative; height:40vh; width:80vw"></canvas>
  </div>
</template>

<script>
import Chart from "chart.js";

export default {
  name: "LijekoviBar",
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
      
      this.pregledi = podaci.lijekoviPoMjesecima;
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

      for (const dio in podaci.lijekoviGodine) {
        this.godineLabele.push(dio);
        this.godinePodaci.push(podaci.lijekoviGodine[dio].toFixed(2));
        this.boje.push("#3cba9f");
        
      }
      this.inicijalizujBar();
    },
    inicijalizujBar() {
      new Chart(this.$refs.lijekoviMjeseci, {
        type: "line",
        data: {
          labels: ["Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust", "Septembar", "Oktobar","Novembar","Decembar"],
          datasets: [
            {
              data:this.pregledi,
              label: "Lijekovi",
              borderColor: "#3e95cd",
              fill: false,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Lijekovi po mjesecima",
          },
        },
      });
      new Chart(this.$refs.lijekoviMjeseciKvartali, {
        type: "line",
        data: {
          labels: ["Prvi kvartal", "Drugi kvartal", "Treci kvartal", "Cetvrti kvartal"],
          datasets: [
            {
              data:this.kvartali,
              label: "Lijekovi",
              borderColor: "#3e95cd",
              fill: false,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Lijekovi po kvartalima",
          },
        },
      });
      new Chart(this.$refs.lijekoviGodine, {
        type: "bar",
        data: {
          labels:this.godineLabele,
          datasets: [
            {
              data:this.godinePodaci,
              label: "Lijekovi",
              backgroundColor: this.boje,
              fill: false,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Lijekovi po godinama",
          },
        },
      });
    },

    




    
  },
  mounted() {},
};
</script>