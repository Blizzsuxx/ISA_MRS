<template>
<el-main>
  <NavMeniZaPacijenta/>
<h2>Apoteke na koje ste pretplaceni</h2>
<div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-input placeholder="Search" v-model="input"></el-input>
    <button v-on:click="pretrazi">Pretrazi</button>
	
  </div>
  <el-table
    ref="zaTabelu"
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
      property="mesto"
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
      property="ocena"
      label="Ocena"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="udaljenost"
      label="Udaljenost"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      label="Prikazi">
      <template #default="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Prikazi</el-button>
        
      </template>
    </el-table-column>
    <el-table-column
      label="Prikazi">
      <template #default="scope">
        <el-button
          size="mini"
          @click="handleEdit2( scope.row)">Otkazi pretplatu</el-button>
        
      </template>
    </el-table-column>
  </el-table>

  <el-row>
  <el-col :span="6"  v-for="item in this.lista" :key="item.opis">
    <el-card :body-style="{ padding: '0px' }">
      <img src="../assets/eksn.png" class="image">
      <div style="padding: 14px;">
        <h4>{{item.stanjeLijeka.lijek.naziv}}</h4>
        <span>{{item.opis}}</span>
        <div class="bottom">
          <time class="time">{{ currentDate }}</time>
         
        </div>
      </div>
    </el-card>
  </el-col>
</el-row>
</el-main>
</template>

const { defineComponent, ref } = Vue;
<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
   import { defineComponent, ref } from 'vue'
export default defineComponent ({
  components :{NavMeniZaPacijenta},
  setup() {
    return {
      input: ref('')
    }
  },
   data() {
      
      return {
       zaTabelu: this.$store.state.APApoteke.sveApoteke,
       lista: [],
       broj: 0
      }
    },
    async mounted(){
      
      await this.$store.dispatch("APPacijenti/dobaviPretplaceneApoteke")
      this.zaTabelu =this.$store.state.APPacijenti.pretplata;
      
    },
    name: 'PretplataNaApoteke',
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      async handleEdit(index, row) {
             console.log(index, row);
             await this.$store.dispatch("APAkcije/dobaviAkcije",row.id).then(response=>{
             //this.$router.push('akcijeApoteke') //potrebno je proslediti apoteku i njene akcije, :()
             this.lista =this.$store.state.APAkcije.akcije;
            console.log(response)
            console.log(this.lista)
            })
      },
      async handleEdit2( row) {
        if(confirm("Da li zelite da otkazete pretplatu na apoteku "+row.ime+ " ?")){
          
          var id=row.id;
          
          await this.$store.dispatch("APPacijenti/otkaziPretplatu",id)
          this.zaTabelu =this.$store.state.APPacijenti.pretplata;
        }
        else{
          console.log("ne");
        }
        
      },
     pretrazi() {
       
        
        if(this.input===""){this.zaTabelu = this.$store.state.APApoteke.sveApoteke;}
        this.zaTabelu = this.$store.state.APApoteke.sveApoteke.filter( (item) => {return (item.ime.includes( this.input) 
        || item.adresa.ulica.includes(this.input)  || item.adresa.Mesto.includes(this.input) || item.adresa.broj.includes(this.input))} );
     },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  })

</script>
