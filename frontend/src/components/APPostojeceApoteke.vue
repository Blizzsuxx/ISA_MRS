<template>
<NavMeniZaPacijenta/>
<el-main>

<h2>Sve registrovane apoteke</h2>
<div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-input placeholder="Search" v-model="input"></el-input>
    <button v-on:click="pretrazi">Pretrazi</button>
	
  </div>
  <el-table
    ref="zaTabelu"
    :data="zaTabelu"
    style="width: 100%">
    <el-table-column
      property="id"
      label="ID"
      width="120">
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
      align="right"> 



      <template #default="scope">
        <el-button
          size="mini"
          type="danger"
          :disabled="scope.row.pretplacen"
          @click="pretplata(scope.$index, scope.row)">{{scope.row.pretplacen?'Pretplacen':'Pretplata'}}</el-button>
      </template>

    </el-table-column>
  </el-table>
</el-main>
</template>

const { defineComponent, ref } = Vue;
<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
   import { defineComponent, ref } from 'vue'
export default defineComponent ({
  setup() {
    return {
      input: ref('')
    }
  },
   data() {
      
      return {
       zaTabelu: this.$store.state.APApoteke.sveApoteke,
      }
    },
    async mounted(){
      
      await this.$store.dispatch("APApoteke/dobaviApotekePacijenta")
      this.zaTabelu =this.$store.state.APApoteke.sveApoteke;
      console.log(this.zaTabelu)
    },
    name: 'APPostojeceApoteke',
    components:{
       NavMeniZaPacijenta
  },
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
      open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno izvrsena pretplata na apoteku.',
          type: 'success'
        })}
      ,
      open2() {
        this.$message({
          showClose: true,
          message: 'Doslo je do greske',
          type: 'error'
        })}
      ,
      pretplata(index, row){
        console.log(row.id);
        row.pretplacen = true;
        this.$store.dispatch("APApoteke/pretplataNaApoteku", row.id)
        .then(response => {
          if (response.data){
            this.open1();
          } else{
            this.open2();
          }
        });
      },
     pretrazi() {
       //ovde ili instalirati onu glupost, koja nzm cemu sluzi, ili poslati beku
       //https://www.npmjs.com/package/vue-input-search
       //https://vuejs.org/v2/guide/forms.html
       //bdw ako stavim : bez () mogu da unesem tekst, ako stavim samo () ne moze ha ha;
        
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
