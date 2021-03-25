<template>
  <el-table
    :ref="referenca"
    height="250"
    :data="lijekovi"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      property="lijek.sifra"
      label="Sifra"
      width="55">
    </el-table-column>
    <el-table-column
      property="lijek.naziv"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
      property="lijek.vrstaLijeka"
      label="Vrsta lijeka"
      width="120">
    </el-table-column>
    <el-table-column
      property="lijek.oblikLijeka"
      label="Oblik lijeka"
      width="120">
    </el-table-column>
    <el-table-column
      property="lijek.sastav"
      label="Sastav"
      width="120">
    </el-table-column>
    <el-table-column
      property="kolicina"
      label="Kolicina"
      width="120">
    </el-table-column>
    

    <el-table-column
      property="lijek.proizvodjac"
      label="Proizvodjac"
      width="120">
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px">
   
    
  </div>
</template>

<script>
export default {
  name: 'LijekoviTabela',
  data() {
      return {
        multipleSelection: []
      }
    },
    props: ['lijekovi','referenca'],
    methods: {
     
      handleSelectionChange(val) {
        this.$emit("promjenjena-selekcija",val)
      }
    },
    emits: ['promjenjena-selekcija']
  }

</script>
