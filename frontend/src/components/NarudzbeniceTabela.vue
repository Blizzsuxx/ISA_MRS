<template>
  <el-table
    :ref="referenca"
    height="250"
    :data="narudzbenice"
    style="width: 100%"
  >
    <el-table-column property="id" label="Sifra">
      
      <template #default="scope">
      <router-link :to="{name:'NarudzbenicaProzor',params:{id:scope.row.id}}">
        {{scope.row.id}}
      </router-link>
      </template>
       </el-table-column>
    <el-table-column property="brojPonuda" label="Broj ponuda">
    </el-table-column>
    <el-table-column property="rok" label="Rok"> </el-table-column>
    <el-table-column property="zavrsena" label="Zavrsena" :formatter="formatirajZavrsenost"> </el-table-column>
    <el-table-column
      fixed="right"
      label="Opcije"
     >
      <template #default="scope">
        <el-button type="primary" plain v-if="scope.row.brojPonuda==0"  @click="()=>izmjeniNarudzbenicu(scope.row.id)"  size="small">Izmjeni</el-button>
        <el-button type="danger" plain  v-if="scope.row.brojPonuda==0" @click="()=>obrisiNarudzbenicu(scope.row.id)"  size="small">Obrisi</el-button>
        <div v-if="scope.row.status===2">
            Odobrena
            </div>
      </template>
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px"></div>
</template>

<script>
export default {
  name: "NarudzbeniceTabela",
  data() {
    return {};
  },
  props: ["narudzbenice", "referenca"],
  methods: {
    formatirajZavrsenost(row) {
      if (row.zavrsena) {
        return "Zavrsena";
      } else {
        return "Nije zavrsena";
      }
    },
    izmjeniNarudzbenicu(id){
      console.log(id)
    },
    obrisiNarudzbenicu(id){
      this.$store.dispatch("Narudzbenice/izbrisiNarudzbenicu",id);
    }
  },
};
</script>
