<template>
<NavMeniZaPacijenta/>
<div class="block">
    <span class="demonstration">Odaberite dan pregleda</span>
    <el-date-picker
      v-model="valueD"
      type="date"
      placeholder="Pick a day">
    </el-date-picker>
  
  <el-time-picker
    is-range
    v-model="value1"
    range-separator="To"
    start-placeholder="Start time"
    end-placeholder="End time">
  </el-time-picker>
  
  </div>
  <el-button @click="handleInfo()">Odaberite datum</el-button>
  <el-table v-if="postojeApoteke" ref="zaTabelu"
    :data="zaTabelu"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      property="ime"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
      property="adresa.Mesto"
      label="Mesto"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="adresa.ulica"
      label="Ulica"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="adresa.broj"
      label="Broj"
      show-overflow-tooltip>
    </el-table-column>
   <el-table-column
      property="ocena"
      label="Ocena"
      show-overflow-tooltip>
    </el-table-column>
     <el-table-column
      property="cena"
      label="cena"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="udaljenost"
      label="Udaljenost"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
              align="right">
            <template #default="scope">
              <el-button
                  size="mini"
                  type="info"
                  @click="odaberiApoteku(scope.row)">Odaberi apoteku</el-button>
            </template>
          </el-table-column>
  </el-table>

  <el-table v-if="postojeFarmaceuti" ref="farmaceuti"
    :data="farmaceuti"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    
    <el-table-column
      property="ime"
      label="Ime"
      width="120">
    </el-table-column>
    <el-table-column
      property="prezime"
      label="Prezime"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="ocena"
      label="Ocena"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
              align="right">
            <template #default="scope">
              <el-button
                  size="mini"
                  type="info"
                  @click="Zakazi(scope.row)">Zakazi</el-button>
            </template>
          </el-table-column>
    </el-table>
    <el-label v-if="nema">{{this.obavestenje}}</el-label>
</template>

<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue";
  export default {
      components : {
       NavMeniZaPacijenta,
       
    },
      name: 'FormaZaZakazivanjeFarmaceut',
    data() {
      return {
        value1: [new Date(2021, 9, 10, 8, 40), new Date(2021, 9, 10, 9, 40)],
        value2: [new Date(2021, 9, 10, 8, 40), new Date(2021, 9, 10, 9, 40)],
        valueD: '',
        postojeApoteke: false,
        postojeFarmaceuti: false,
        zaTabelu: this.$store.state.APApoteke.sveApoteke,
        farmaceuti:[],
        obavestenje: "",
        nema: false
      };
    },
    async mounted(){
      
      await this.$store.dispatch("APApoteke/dobaviApoteke")
      this.zaTabelu =this.$store.state.APApoteke.sveApoteke;
      
    },
    methods: {
      async handleInfo() {
      var dan=""+this.value1+" "+this.value2+" "+this.valueD
      await this.$store.dispatch("APApoteke/dobaviSlobodneTermineFarmaceut", dan)
      this.zaTabelu =this.$store.state.APApoteke.slobodneApoteke;
      if(this.zaTabelu.length==0){
          this.obavestenje="Ne postoje slobodni termini u trazenom periodu."
          this.nema=true;
      }else{
      this.postojeApoteke=true;}
    },
    async odaberiApoteku(row) {
      
      console.log(row)
      await this.$store.dispatch("APApoteke/dobaviFarmaceute", row.id)
      this.farmaceuti=this.$store.state.APApoteke.farmaceuti
      if(this.farmaceuti.length!=0){
      this.postojeApoteke=false;
      this.postojeFarmaceuti=true;
      }
      else{
          this.obavestenje="Ne postoje slobodni farmaceuti u trazenom periodu."
          this.nema=true;
      }
    },
    zakazi(row){
        this.$store.dispatch("APPosete/zakaziPosetu", row.username)
        var oznaka=this.$store.state.APApoteke.oznaka;
        if(oznaka){
            alert("Rezervisali ste termin");
            this.$store.dispatch("Mail/posaljiMail", {"text": "Zakazali ste pregled kod rafmaceuta:" + row.firstname, "address" : "rajtarovnatasa@gmail.com"})
      
            }else{
                alert("Niste rezervisali termin");
            }
        
        //mejl if
    }
    }
  }
</script>