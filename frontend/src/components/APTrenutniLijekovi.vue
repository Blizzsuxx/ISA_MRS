<template>
<h2>Lekovi koji su trenutno u ponudi</h2>
  <el-table
    ref="multipleTable1"
    :data="this.$store.state.APlijekovi.lijekoviProdaja"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      property="lijek.sifra"
      label="Sifra"
      width="120">
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
      show-overflow-tooltip>
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-button @click="toggleSelection()">Očisti selekciju</el-button>
    <el-button @click="ukloniIzProdaje()">Premesti u magacin</el-button>
  </div>
</template>

<script>
export default {
  name: 'APTrenutniLijekovi',
  data() {
      return {
        multipleSelection1: []
      }
    },
    
    methods: {
     
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable1.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable1.clearSelection();
        }
      },
      ukloniIzProdaje(){
        
        this.$store.dispatch("APlijekovi/izbaciIzProizvodnje",this.multipleSelection1)
       
        this.$refs.multipleTable1.clearSelection();
      },
      handleSelectionChange(val) {
        this.multipleSelection1 = val;
      }
    }
  }

</script>
