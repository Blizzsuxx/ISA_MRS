<template>
<el-main>
  <NavMeniZaPacijenta/>
<h2>Rezervisanje leka</h2>
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
    </el-table-column>

    <el-table-column
      property="imeApoteke"
      label="Apoteka"
     
     >
    </el-table-column>
     <el-table-column
      label="Rezervisi">
      <template #default="scope">
        <el-button
          size="mini"
          @click="handleEdit( scope.row)">Rezervisi</el-button>
        
      </template>
     </el-table-column>
  </el-table>
  <ModalniProzorKolicinaleka  ref="prozor"/>
</el-main>
</template>

<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
import ModalniProzorKolicinaleka from './modal/ModalniProzorKolicinaleka.vue'

export default {
  name: 'FormaRezervisanjLekova',
  
  components: {NavMeniZaPacijenta, ModalniProzorKolicinaleka},
  data() {
      return {
        multipleSelection: [],
        search : '',
        lijekovi : []
        
      }
    },
    
    async mounted(){
      //pozivanje ucitavanja podataka poseta
      await this.$store.dispatch("APlijekovi/dobaviSveDostupneLijekove")
      this.lijekovi = this.$store.state.APlijekovi.dostupniLekovi;
      
    },
    methods: {

     
      handleEdit( row){
      //let lijek=row.id;
      console.log(row )
      console.log("nnn")
      var apoteka=row.imeApoteke
      console.log(apoteka)
      this.$refs.prozor.ocenjivac.id = row.id
      this.$refs.prozor.ocenjivac.apoteka = row.apoteka
      this.$refs.prozor.dostupno=row.kolicina
      this.$refs.prozor.modalOpen = true;
      //this.$store.dispatch("APlijekovi/rezervisiLek",lijek, apoteka,"gg")
     // this.$store.dispatch("Mail/posaljiMail", {"text": "Rezervisali ste lek " + lijek+" iz apoteke: "+apoteka, "address" : "rajtarovnatasa@gmail.com"})
     

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
