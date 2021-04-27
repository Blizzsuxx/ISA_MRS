<template>
  <AANavMeni />
  <el-row :gutter="20">
    <el-col :span="12">
      <h5>Dermatolozi</h5>
      <DermatoloziTabela
        @promjenjena-selekcija="promjenaDermatologa"
        v-bind:dermatolozi="$store.state.Dermatolozi.sviDermatolozi"
    /></el-col>
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
import AANavMeni from "./AANavMeni";
import DermatoloziTabela from "./DermatoloziTabela";
import { mapState } from "vuex";
export default {
  name: "BrisanjeDermatologa",
  props: { options: String },
  data() {
    return {
      apotekaUcitana: false,
      ucitavanjeSlobodnogTermina: false
    };
  },
  methods: {},
  components: {
    AANavMeni,
    DermatoloziTabela,
   
  },
  mounted() {
    this.$store.dispatch("Dermatolozi/dobaviDermatologeAdmin");
  },

  computed: {
    ...mapState("APApoteke", ["apoteka"]),
  },
};
</script>