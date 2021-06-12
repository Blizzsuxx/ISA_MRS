<template>
 <NavAdminApoteke />
  <el-table
    height="500"
    :data="$store.state.APlijekovi.poruceniNepostojeci"
    style="width: 100%"
  >
    <el-table-column property="id" label="Sifra">
      
       </el-table-column>
    <el-table-column property="lijek.naziv" label="Lijek">
    </el-table-column>
    <el-table-column property="imeApoteke" label="Ime apoteke"> </el-table-column>
    <el-table-column property="zatrazen" label="Zatrazen" > </el-table-column>
    <el-table-column property="zatrazenDatum" label="Datum kada je zatrazen" > </el-table-column>
    <el-table-column
      fixed="right"
      label="Opcije"
     >
      <template #default="scope">
        <el-button type="primary" plain   @click="()=>pregledano(scope.row.id)"  size="small">Pregledano</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px"></div>
</template>


<script>
import NavAdminApoteke from "./NavAdminApoteke";
export default {
  name: "PoruceniNepostojeci",
  data() {
    return {};
  },
  components:{
    NavAdminApoteke,
  },
  methods: {
      pregledano(id){
          this.$store.dispatch("APlijekovi/oznaciPregledano",id)
      }
  },
  mounted() {
      this.$store.dispatch("APlijekovi/dobaviPoruceneNepostojece")
  }
};
</script>
