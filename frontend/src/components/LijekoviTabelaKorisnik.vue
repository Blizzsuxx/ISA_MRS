<template>
<el-input placeholder="Search table" v-model="search"></el-input>
  <el-table
    :ref="referenca"
    height="250"
    :data="lijekovi.filter(data => !search || String(data.lijek.naziv).toLowerCase().includes(search.toLowerCase()) ||
    String(data.lijek.id).toLowerCase().includes(search.toLowerCase()) ||
    String(data.lijek.vrstaLijeka).toLowerCase().includes(search.toLowerCase()) ||
    String(data.lijek.oblikLijeka).toLowerCase().includes(search.toLowerCase()) ||
    String(data.lijek.sastav).toLowerCase().includes(search.toLowerCase())
    )"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
     >
    </el-table-column>
    
    <el-table-column
      property="lijek.id"
      label="Sifra"
     >
    </el-table-column>
    <el-table-column
      property="lijek.naziv"
      label="Naziv"
      >
    </el-table-column>
    <el-table-column
      property="lijek.vrstaLijeka"
      label="Vrsta lijeka"
      >
    </el-table-column>
    <el-table-column
      property="lijek.oblikLijeka"
      label="Oblik lijeka"
      >
    </el-table-column>
    <el-table-column
      property="lijek.sastav"
      label="Sastav"
      >
    </el-table-column>
    <el-table-column
      property="kolicina"
      label="Kolicina"
      >
    </el-table-column>
    

    <el-table-column
      property="lijek.proizvodjac"
      label="Proizvodjac"
     >
    </el-table-column>
    <el-table-column
      property="cijena"
      label="Cijena"
     >
      <template #default="scope">
          <p v-if="scope.row.akcija===null">{{scope.row.cijena}}</p>
          <p v-else><strike>{{scope.row.cijena}}</strike>     <span class="akcija">{{izracunajCijenu(scope.row)}}</span></p>
        </template>
    </el-table-column>

    <el-table-column
      property="datumIstekaCijene"
      label="Datum isteka cijene"
     
     >
    </el-table-column>    
  </el-table>
  <div style="margin-top: 20px">
   
  </div>
</template>
<style scoped>
  .akcija{
    color:red;
  }
</style>
<script>
export default {
  name: 'LijekoviTabelaKorisnik',
  data() {
      return {
        multipleSelection: [],
        search : ''
      }
    },
    props: ['lijekovi','referenca'],
    methods: {
     
      handleSelectionChange(val) {
        this.$emit("promjenjena-selekcija",val)
      },
      formatirajProizvodnju(row){
      
      if(row.prodaja){
        return "Prodaja"
      }
      else{
        return "Magacin"
      }
      },
      izracunajCijenu(row){
          return (row.cijena*(1-row.akcija.procenatPopusta/100)).toFixed(1)
      },
      formirajDatum(row){
        try{
          let podjeljeno = row.datumIstekaCijene.split("T")
        return podjeljeno.join(" ")
        }
        catch(error){
          return " "
        }
        
      }
    },
    
  
    emits: ['promjenjena-selekcija']
  }
  

</script>
