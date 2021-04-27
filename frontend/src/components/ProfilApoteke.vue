<template>
  <NavAdminApoteke />
  <el-row :gutter="20">
    <el-col :span="24"
      ><div v-if="apotekaUcitana">
        <h1>{{ apoteka.ime }}</h1>
      </div></el-col
    >
    <el-col :span="24"
      ><div v-if="apotekaUcitana">
        <h3>
          <i></i>{{ apoteka.ulica }} {{ apoteka.broj }}, {{ apoteka.mjesto }}
        </h3>
      </div></el-col
    >
  </el-row>

  <el-row :gutter="20">
    <el-col :span="12">
      <h5>Dermatolozi</h5>
      <DermatoloziTabela
        @promjenjena-selekcija="promjenaDermatologa"
        v-bind:dermatolozi="$store.state.Dermatolozi.sviDermatolozi"
    /></el-col>
    <el-col :span="12">
      <h5>Farmaceuti</h5>
      <DermatoloziTabela
        @promjenjena-selekcija="promjenaDermatologa"
        v-bind:dermatolozi="$store.state.Farmaceuti.sviFarmaceuti"
      />
    </el-col>
  </el-row>
  <el-row :gutter="20">
    <el-col :span="24"
      ><div v-if="apotekaUcitana">
        <h3>Lista dostupnih lijekova</h3>
        <LijekoviTabela
          @promjenjena-selekcija="selektujRedove"
          ref="dijete"
          v-bind:lijekovi="$store.state.APlijekovi.sviLijekovi"
          referenca="multipleTable"
        /></div
    ></el-col>
  </el-row>

   <el-row :gutter="20">
    <el-col :span="12">
   
      <div id="app">
      <el-card class="box-card" v-if="ucitavanjeSlobodnogTermina">
        <template #header>
          <div class="card-header">
            Slobodni termini
          </div>
        </template>
        <div
          v-for="slobodanTermin in $store.state.APApoteke.slobodniTermini"
          :key="slobodanTermin.id"
          class="text item"
        >
          <p>
            Vrijeme termina:{{ slobodanTermin.pocetakTermina }}-{{
              slobodanTermin.krajTermina
            }}
          </p>
          <p>Cijena termina:{{ slobodanTermin.cijenaTermina }}</p>
          <hr />
        </div>
      </el-card>
      </div>
     </el-col>
    <el-col :span="12">
     
    </el-col>
  </el-row>

  <el-row :gutter="20">
    <el-col :span="24"
      ><b>Ocjena: 4.5/5</b>
    </el-col>
  </el-row>
</template>
<style scoped>
.item {
  margin-bottom: 18px;
}

.box-card {
  width: 600px;
 
}
.poravnanje {
  align-content: center;
  align-items: center;
  text-align: center;
}
#app {
        position: relative;
        
        
        display:flex;
        justify-content: center;
        align-items: center;
    }
</style>

<script>
import LijekoviTabela from "./LijekoviTabela";
import NavAdminApoteke from "./NavAdminApoteke";
import DermatoloziTabela from "./DermatoloziTabela";
import { mapState } from "vuex";
export default {
  name: "Selecter",
  props: { options: String },
  data() {
    return {
      apotekaUcitana: false,
      ucitavanjeSlobodnogTermina: false
    };
  },
  methods: {},
  components: {
    NavAdminApoteke,
    DermatoloziTabela,
    LijekoviTabela,
  },
  mounted() {
    this.$store.dispatch("Dermatolozi/dobaviDermatologe");
    this.$store.dispatch("APlijekovi/dobaviLijekove");
    this.$store.dispatch("APApoteke/dobaviApoteku", 1).then(() => {
      this.apotekaUcitana = true;
      console.log(this.$store.state.APApoteke.apoteka);
    });
    this.$store
        .dispatch("APApoteke/dobaviSlobodneTermine", 1)
        .then(() => {
          this.ucitavanjeSlobodnogTermina = true;});
  },

  computed: {
    ...mapState("APApoteke", ["apoteka"]),
  },
};
</script>