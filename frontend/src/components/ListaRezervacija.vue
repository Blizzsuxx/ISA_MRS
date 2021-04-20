<template>
<el-menu :default-active="activeIndex" 
     class="el-menu-demo" mode="horizontal" @select="handleSelect"
     background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1">
  <el-menu-item index="1">Pocetna stranica</el-menu-item>
  <el-menu-item index="2">Profil</el-menu-item>
  
</el-menu>
<h2>Lista rezervacija leka</h2>
  <el-table
    ref="multipleTable1"
    :data="this.$store.state.RezervisaniLekovi.rezervisani"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55"> <!--deo za selekciju-->
    </el-table-column>
    <el-table-column
      property="nazivLeka"
      label="Naziv leka"
      width="120">
    </el-table-column>
    <el-table-column
      property="vrstaLeka"
      label="Vrsta leka"
      width="120">
    </el-table-column>
    <el-table-column
      property="kolicina"
      label="Kolicina"
      width="120">
    </el-table-column>
    <el-table-column
      property="nazivApoteke"
      label="Naziv apoteke"
      width="120">
    </el-table-column>
    <el-table-column
      property="datumVazenja"
      label="Datum vazenja rezervacije"
      width="120">
    </el-table-column>
    
    <el-table-column
      property="cena"
      label="Cena"
      width="120"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      label="Odustani">
      <template #default="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Odustani</el-button>
        
      </template>
    </el-table-column>
  </el-table>

</template>

<script>
export default {
  name: 'ListaRezervacija',
  async mounted(){
     await this.$store.dispatch("RezervisaniLekovi/dobaviRezervacije")
    },
  data() {
      return {
        multipleTable1: []
      }
    },
    
    methods: {
     
       handleEdit(index, row) {
             //todo, smisli sta je row, a sta index, tj prosledi ime apoteke, ime leka, i datum
             
        this.$store.dispatch("RezervisaniLekovi/odustani",index, row)
      },
     
      handleSelectionChange(val) {
        this.multipleTable1 = val;
      }
    }
  }

</script>
