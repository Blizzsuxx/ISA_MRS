<template>
  <div  width="100" height="100">
    <canvas ref="doughnut"  style="position: relative; height:40vh; width:80vw"></canvas>
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
      boje: new Array(),
    };
  },
  methods: {
    iscrtaj(podaci) {
      
      this.labele.push("Apoteka");
      this.podaci.push(podaci.ocjenaApoteke.toFixed(2));
      this.boje.push("#3e95cd");
      for (const dio in podaci.ocjeneDermatologa) {
        this.labele.push(dio);
        this.podaci.push(podaci.ocjeneDermatologa[dio].toFixed(2));
        this.boje.push("#8e5ea2");
      }
      for (const dio in podaci.ocjeneFarmaceuta) {
        this.labele.push(dio);
        this.podaci.push(podaci.ocjeneFarmaceuta[dio].toFixed(2));
        this.boje.push("#3cba9f");
      }

      this.inicijalizujBar();
    },
    inicijalizujBar() {
      new Chart(this.$refs.doughnut, {
        type: "horizontalBar",
        data: {
          labels: this.labele,
          datasets: [
            {
              label: "Prosjecna ocjena (od 0 do 5)",
              backgroundColor: this.boje,
              data: this.podaci,
            },
          ],
        },
        options: {
          legend: {
            display: false,
          },
          title: {
            display: true,
            text: "Prosjecne ocjene apoteke i zaposlenih",
          },
        },
      });
    },
  },
  mounted() {},
};
</script>