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
  
  
   <el-dialog
    title="Kolicina leka"
    v-model="centerDialogVisible"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="lijek"
          >
            <el-form-item label="Kolicina">
              <el-input-number v-model="this.ocenjivac.kolicina"  @change="handleChange" :min="1" :max="this.dostupno"></el-input-number>
            </el-form-item>
            <el-form-item label="Preuzimanja">
              <el-date-picker
                v-model="this.ocenjivac.value2"
                type="date"
                :shortcuts="shortcuts"
                
                align="right"
               >
              </el-date-picker>
            </el-form-item>
          </el-form>
    </div>
    <div>
     
    </div>
    <template #footer>
      <span class="dialog-footer">
      
        <el-button type="primary" @click="potvrdi2">Potvrdi</el-button>
      </span>
    </template>

  </el-dialog>
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
        lijekovi : [],
        id : parseInt(this.$route.params.id),
        centerDialogVisible: false,
         dostupno:0,
      ocenjivac: {
        id:"",
        apoteka: 0,
        value2:"",
        kolicina: 0
      },
        
      }
    },
    
    async mounted(){
      //pozivanje ucitavanja podataka poseta
    
      
      if(typeof this.id!=="number" || isNaN(this.id)){
      await this.$store.dispatch("APlijekovi/dobaviSveDostupneLijekove")
      this.lijekovi = this.$store.state.APlijekovi.dostupniLekovi;
      }
      else{
        this.$store.dispatch("APlijekovi/dobaviLijekoveKorisnik",this.id).then(()=>{
            
          this.lijekovi = this.$store.state.APlijekovi.sviLijekovi;
        })
      }
      
    },
    methods: {

     
      handleEdit( row){
      //let lijek=row.id;
      console.log(row )
      console.log("nnn")
      var apoteka=row.imeApoteke
      console.log(apoteka)
      this.ocenjivac.id = row.id//$refs.prozor
      this.ocenjivac.apoteka = row.apoteka
      this.dostupno=row.kolicina
      if(this.dostupno<=0){
        this.$message({
                type: 'normal',
                message: 'Trenutno nema leka na stanju, molimo Vas rezervisite drugi lek.'
              });
        this.$store.dispatch("APlijekovi/setujPotraznju",row.id);
      }
      //this.$refs.prozor.modalOpen = true;
      //this.$store.dispatch("APlijekovi/rezervisiLek",lijek, apoteka,"gg")
     // this.$store.dispatch("Mail/posaljiMail", {"text": "Rezervisali ste lek " + lijek+" iz apoteke: "+apoteka, "address" : "rajtarovnatasa@gmail.com"})
      else{ this.centerDialogVisible=true;}

      },
      async potvrdi2(){ //refresh i sacuvaj 
       this.centerDialogVisible=false;
        if(this.ocenjivac.kolicina>this.dostupno){
           this.$message({
                type: 'danger',
                message: 'Trenutno nema '+this.ocenjivac.kolicina+' leka na stanju.'
              });
        }else{
          
        var podaci=this.ocenjivac.value2+" "+this.ocenjivac.id+" "+this.ocenjivac.apoteka+" "+this.ocenjivac.kolicina;
        console.log(podaci)
        this.$store.dispatch("APlijekovi/rezervisiLek",podaci).then(response=>{

           if(response==true){//todo potrebno je da mi se negde vrati
             this.$store.dispatch("Mail/posaljiMail", {"text": "Rezervisali ste lek  iz apoteke: "+this.ocenjivac.apoteka, "address" : "rajtarovnatasa@gmail.com"})
              var i=0;
              for(i=0;i<this.lijekovi.length;i++){
                 console.log("bbbbbbbbbbbbb")
                if(this.lijekovi[i].id===this.ocenjivac.id){
                 
                  this.lijekovi[i].kolicina=this.lijekovi[i].kolicina-this.ocenjivac.kolicina;
                   console.log(this.lijekovi[i].kolicina)
                  break;
                }
              }
           }else{
           this.$message({
                type: 'danger',
                message: 'Rezervacija nije uspela.'
              });
           }
         })}
      },
      handleChange(value){console.log(value)
       this.ocenjivac.kolicina=value
      },//ovo je za kolicinu
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
