<template>
  <NavAdminApoteke />
  <el-row :gutter="20">
    <el-col :span="24">
      <h5>Dermatolozi</h5>
      <DermatoloziTabela
        @promjenjena-selekcija="promjenaDermatologa"
        v-bind:dermatolozi="$store.state.Dermatolozi.sviDermatolozi"
    /></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-button type="success" @click="potvrdjeno" plain>Zapošljavanje dermatologa</el-button>
    </el-col>
  </el-row>
  <RadnoVrijeme ref="prozor" :dermatolog ="dermatolog"/>
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

  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<script>
import NavAdminApoteke from "./NavAdminApoteke";
import DermatoloziTabela from "./DermatoloziTabela";
import RadnoVrijeme from "./modal/RadnoVrijeme"
import { mapState } from "vuex";
export default {
  name: "DermatologZaposljavanje",
  props: { options: String },
  data() {
    return {
      dermatolog : null
    }
  },
  methods: {
    promjenaDermatologa (val) {
      this.dermatolog = val
      
    },
    potvrdjeno(){
       this.$refs.prozor.modalOpen = true;
       this.$refs.prozor.radnoVrijeme.radnik = this.dermatolog.id;
    }
  },
  components: {
    NavAdminApoteke,
    DermatoloziTabela,
    RadnoVrijeme
  },
  mounted() {
    this.$store.dispatch("Dermatolozi/dobaviNezaposleneDermatologe");
  },

  computed: {
    ...mapState("APApoteke", ["apoteka"]),
  },
};
</script>