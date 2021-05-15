<template>
  <el-row :gutter="20">
    <el-col :span="8">
      <el-input placeholder="Pretraži tabelu" v-model="search"></el-input>
    </el-col>
    <el-col :span="8">
      <el-select v-model="apotekaFilter" clearable placeholder="Apoteka">
        <el-option
          v-for="apoteka in apoteke"
          :key="apoteka.id"
          :label="apoteka.ime"
          :value="apoteka.ime"
        >
        </el-option>
      </el-select>
    </el-col>
    <el-col :span="8">
      <div class="block">
        <span class="demonstration">Ocjena dermatologa</span>
        <el-slider v-model="value" range show-stops :max="10"> </el-slider>
      </div>
    </el-col>
  </el-row>

  <el-table
    ref="referenca"
    highlight-current-row
    @current-change="handleCurrentChange"
    :data="dermatolozi.filter((data) => filtrirajTabelu(data))"
    style="width: 100%"
  >
    <el-table-column property="korisnickoIme" label="Korisničko ime">
    </el-table-column>
    <el-table-column property="ime" label="Ime"> </el-table-column>
    <el-table-column property="prezime" label="Prezime"> </el-table-column>
    <el-table-column property="email" label="Email"> </el-table-column>
    <el-table-column property="rodjendan" label="Datum rođenja">
    </el-table-column>
    <el-table-column property="apoteke" label="Apoteke">
      <template #default="scope">
        <ul>
          <li v-for="apoteka in scope.row.apoteke" :key="apoteka">
            {{ apoteka }}
          </li>
        </ul>
      </template>
    </el-table-column>
    <el-table-column property="ocjena" label="Ocjena">
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px"></div>
</template>

<script>
export default {
  name: "DermatoloziTabela",
  data() {
    return {
      search: "",
      apotekaFilter: "",
      apoteke: {},
      value: [0, 10],
    };
  },
  components: {},
  props: ["dermatolozi", "referenca"],
  mounted() {
    this.$store.dispatch("APApoteke/dobaviApoteke").then(() => {
      this.apoteke = this.$store.state.APApoteke.sveApoteke;
    });
  },
  methods: {
    handleCurrentChange(val) {
      this.$refs.referenca.setCurrentRow(val);
      this.$emit("promjenjena-selekcija", val);
    },
    filtrirajTabelu(podaci) {
      if (this.search) {
        if (
          String(podaci.ime)
            .toLowerCase()
            .includes(this.search.toLowerCase()) ||
          String(podaci.prezime)
            .toLowerCase()
            .includes(this.search.toLowerCase()) ||
          String(podaci.korisnickoIme)
            .toLowerCase()
            .includes(this.search.toLowerCase()) ||
          String(podaci.email).toLowerCase().includes(this.search.toLowerCase())
        )
          return true;
        return false;
      }
      if (this.apotekaFilter) {
        if (
          podaci.apoteke.filter((temp) => temp === this.apotekaFilter)
            .length !== 0
        )
          return true;
        return false;
      }
      return true;
    },
  },
  emits: ["promjenjena-selekcija"],
};
</script>
