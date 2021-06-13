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
      <el-popconfirm
        title="Da li ste sigurni da želite da otpustitite dermatologa? Ako dermatolog ima zakazane preglede svi će biti
        otkazani i moguće je nezadovoljstvo kod pacijenata."
        @confirm="potvrdjeno"
        confirmButtonText="Potvrdi"
        cancelButtonText="Odustani"
      >
        <template #reference>
          <el-button type="danger" plain>Otpuštanje dermatologa</el-button>
        </template>
      </el-popconfirm>
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

  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<script>
import NavAdminApoteke from "./NavAdminApoteke";
import DermatoloziTabela from "./DermatoloziTabela";
import { mapState } from "vuex";
export default {
  name: "BrisanjeDermatologa",
  props: { options: String },
  data() {
    return {
      dermatolog: null,
    };
  },
  methods: {
    promjenaDermatologa(val) {
      this.dermatolog = val;
    },
    potvrdjeno() {
      this.$store
        .dispatch("Dermatolozi/otpustiDermatologa", this.dermatolog.id)
        .then(() => {
          this.$store.dispatch("Dermatolozi/dobaviDermatologeAdmin");
          this.$message({
            showClose: true,
            message: "Uspjesno ste otpustili dermatologa.",
            type: "success",
          });
        });
    },
  },
  components: {
    NavAdminApoteke,
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