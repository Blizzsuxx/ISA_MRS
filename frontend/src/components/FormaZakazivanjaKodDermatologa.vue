<template>
<el-main>
  <NavMeniZaPacijenta/>
<h2>Zakazivanje pregleda kod dermatologa</h2>
<el-input placeholder="Search table" v-model="search"></el-input>
  <el-table
    :ref="referenca"
    height="250"
    :data="posete.filter(data => !search || String(data.lijek.naziv).toLowerCase().includes(search.toLowerCase()) ||
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
      property="imeRadnika"
      label="Ime dermatologa"
     >
    </el-table-column>
    <el-table-column
      property="prezimeRadnika"
      label="Prezime dermatologa"
     ></el-table-column>
    <el-table-column
      property="pocetakTermina"
      label="Pocetak posete"
     
      >
    </el-table-column>
    <el-table-column
      property="krajTermina"
      label="Kraj posete"
      
      >
    </el-table-column>
    <el-table-column
      property="cijenaTermina"
      label="Cena"
      >
    </el-table-column>
    <el-table-column
      property="ocenaRadnika"
      label="Ocena"
      >
    </el-table-column>
    <el-table-column
      property="apoteka.ime"
      label="Apoteka"
      >
    </el-table-column>
    
     <el-table-column
      label="Rezervisi">
      <template #default="scope">
        <el-button
          size="mini"
          @click="handleEdit( scope.row)">Zakazi</el-button>
        
      </template>
     </el-table-column>
  </el-table>
 
</el-main>
</template>

<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
export default {
  components: {NavMeniZaPacijenta},
  name: 'FormaRakazivanjaKodDermatologa',
  data() {
      return {
        multipleSelection: [],
        search : '',
        posete: []
        
      }
    },
    
    async mounted(){
      //pozivanje ucitavanja podataka poseta
      await this.$store.dispatch("APPosete/dobaviSlobodnePoseteDermatologa")
      this.posete = this.$store.state.APPosete.slobodnePoseteD;
      console.log(this.posete)
      
    },
    methods: {
      

     
      handleEdit( row){
      let id=row.id;
      this.$store.dispatch("APPosete/rezervisiPosetuD",id).then(response=>{
        if(response){
      this.$store.dispatch("Mail/posaljiMail", {"text": "Zakazali ste pregled kod dermatologa:" + row.radnik.ime+" iz apoteke: "+row.radnik.prezime, "address" : "rajtarovnatasa@gmail.com"})
      
        }
     })
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
    
  
    emits: ['promjenjena-selekcija']
  }
  

</script>
