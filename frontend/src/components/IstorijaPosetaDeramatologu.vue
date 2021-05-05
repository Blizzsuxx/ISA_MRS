<template>
        
      <el-main>
        <NavMeniZaPacijenta/>

        <h2>Istorija poseta dermatologu</h2>
        <el-table :data="tableData">
          <el-table-column prop="pocetak" label="Pocinje"
          :formatter="formirajDatum" >
          </el-table-column>
          <el-table-column prop="kraj" label="Zavrsetak" 
          :formatter="formirajDatum">
          </el-table-column>
          <el-table-column prop="radnik.firstName" label="Ime" >
          </el-table-column>
      <el-table-column prop="radnik.lastName" label="Prezime" >
          </el-table-column>
          <el-table-column prop="apoteka.ime" label="Apoteka">
          </el-table-column>
        <el-table-column prop="cena" label="Cena">
          </el-table-column>
          <el-table-column
              align="right">
            <template #default="scope">
              <el-button
                  size="mini"
                  type="info"
                  @click="handleInfo(scope.$index, scope.row)">Detalji</el-button>
              
              
            </template>
          </el-table-column>


        </el-table>

        <div id="app">
      <el-card class="box-card" v-if="postojiOpis">
        <template #header>
          <div class="card-header">
            Opis pregleda
          </div>
        </template>
        <p>{{this.opis}}</p>
      </el-card>
      </div>

         <h2>Pregled prepisanih lekova</h2>
         <el-table
    :ref="referenca"
    height="250"
    :data="lijekovi"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
     >
    </el-table-column>
    
    <el-table-column
      property="id"
      label="Sifra"
     >
    </el-table-column>
    <el-table-column
      property="naziv"
      label="Naziv"
      >
    </el-table-column>
    <el-table-column
      property="vrstaLijeka"
      label="Vrsta lijeka"
      >
    </el-table-column>
    <el-table-column
      property="oblikLijeka"
      label="Oblik lijeka"
      >
    </el-table-column>
    <el-table-column
      property="sastav"
      label="Sastav"
      >
    </el-table-column>

    <el-table-column
      property="proizvodjac"
      label="Proizvodjac"
     >
    </el-table-column>
    <el-table-column
      property="cijena"
      label="Cijena"
     >
    </el-table-column>


  </el-table>
      </el-main>
    
</template>


<script>

import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue";
  export default {
    name: 'IstorijaPosetaDermatologu',
    components : {
       NavMeniZaPacijenta,
       
    },

    async mounted(){
      //pozivanje ucitavanja podataka poseta
      await this.$store.dispatch("APPosete/dobaviIstorijuD")
      this.tableData = this.$store.state.APPosete.istorijaDermatolog;
      
    },

    methods: {
      handleInfo(index) {
      console.log(this.tableData[index].prepisaniLekovi);
      this.lijekovi=this.tableData[index].prepisaniLekovi;
      this.postojiOpis=true;
       this.opis=this.tableData[index].opis;
    },
      formirajDatum(row){
        try{
          let podjeljeno = row.pocetak.split("T")
        return podjeljeno.join(" ")
        }
        catch(error){
          return " "
        }
        
      }   
    },
        data() {
      return {
        tableData: this.$store.state.APPosete.istorijaDermatolog,
        lijekovi: [],
        postojiOpis: false,
        opis:''
      }
    }
  };
</script>