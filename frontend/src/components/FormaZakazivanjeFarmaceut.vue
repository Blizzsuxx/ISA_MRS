<template>
<NavMeniZaPacijenta/>
<div class="block">
    <span class="demonstration">Odaberite dan pregleda </span>
    <el-date-picker
      v-model="valueD"
      type="date"
      placeholder="Pick a day">
    </el-date-picker>
  
  <el-time-picker
    v-model="value1"
    
    placeholder="Start">
  </el-time-picker>
  <el-time-picker
    v-model="value2"
    
    placeholder="End">
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
      property="mjesto"
      label="Mesto"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="ulica"
      label="Ulica"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="broj"
      label="Broj"
      show-overflow-tooltip>
    </el-table-column>
   <el-table-column
   sortable
      property="ocena"
      label="Ocena"
      show-overflow-tooltip>
    </el-table-column>
     <el-table-column
     sortable
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
    sortable
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
                  @click="zakazi(scope.row)">Zakazi</el-button>
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
      async handleInfo() {this.zaTabelu=[];
      let dan=new Date(this.valueD)
      console.log(this.value2)
      let vreme=(this.value1+"").split(" ");
      let vreme2=(this.value2+"").split(" ");
      let vr1=""+vreme[4];
      let v1=vr1.split(":"); vr1=""+v1[0]+" "+v1[1]
      let vr2=""+vreme2[4]
      let v2=vr2.split(":"); vr2=""+v2[0]+" "+v2[1]
      dan+=" "+vr1+" "+vr2;
      let konacno=dan.split(" "); 
      
      dan=konacno[1]+" "+konacno[2]+" "+konacno[3]+" "+konacno[10]+" "+konacno[11]+" "+konacno[12]+" "+konacno[13]
      //console.log(dan)
      await this.$store.dispatch("APApoteke/dobaviSlobodneTermineFarmaceut", dan)
      //console.log(this.$store.state.APApoteke.slobodneApoteke)
      this.zaTabelu =this.$store.state.APApoteke.slobodneApoteke;
      if(this.zaTabelu.length==0){
          this.obavestenje="Ne postoje slobodni termini u trazenom periodu."
          this.nema=true;
      }else{
      this.postojeApoteke=true;}
    },
    async odaberiApoteku(row) {
      
      console.log(row)
      await this.$store.dispatch("APApoteke/dobaviFarmaceute", row.ime) //TODO popravi na id
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
    async zakazi(row){
      console.log(row.ime)
        var osoba=row.ime+" "+row.prezime;
        await this.$store.dispatch("APApoteke/zakaziPosetu", osoba) //ovo prebaci u username
        var oznaka=this.$store.state.APApoteke.oznaka;
        if(oznaka){
           this.$message({
                type: 'danger',
                message: 'Rezervisali ste termin.'
              });
            this.$store.dispatch("Mail/posaljiMail", {"text": "Zakazali ste pregled kod rafmaceuta:" + row.ime+" "+row.prezime, "address" : "rajtarovnatasa@gmail.com"})
      
            }else{
               this.$message({
                type: 'danger',
                message: 'Niste rezervisali termin.'
              });
            }
        
        //mejl if
    }
    }
  }
</script>