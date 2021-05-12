<template>
<el-input placeholder="Pretrazi tabelu" v-model="search"></el-input>
  <el-table
    ref="referenca"
    highlight-current-row
    @current-change="handleCurrentChange"
    
    :data="dermatolozi.filter(data => !search || String(data.ime).toLowerCase().includes(search.toLowerCase()) ||
    String(data.prezime).toLowerCase().includes(search.toLowerCase()) ||
    String(data.korisnickoIme).toLowerCase().includes(search.toLowerCase()) ||
    String(data.email).toLowerCase().includes(search.toLowerCase()) 
    )"
    style="width: 100%"
   >
   
    <el-table-column
      property="korisnickoIme"
      label="Korisnicko ime"
     >
    </el-table-column>
    <el-table-column
      property="ime"
      label="Ime"
      >
    </el-table-column>
    <el-table-column
      property="prezime"
      label="Prezime"
      >
    </el-table-column>
    <el-table-column
      property="email"
      label="Email"
      >
    </el-table-column>
    
    


    
    
  </el-table>
  <div style="margin-top: 20px">
   
    
  </div>
</template>

<script>


export default {
  name: 'DermatoloziTabela',
  data() {
      return {
        search : ''
      }
    },
  components:{
  
  },
  props: ['dermatolozi','referenca'],
  methods: {
      formirajDatum(row){
        try{
          let podjeljeno = row.rodjendan.split("T")
        return podjeljeno.join(" ")
        }
        catch(error){
          return " "
        }
        
      },
      handleCurrentChange(val) {
        this.$refs.referenca.setCurrentRow(val)
        this.$emit("promjenjena-selekcija",val)
      },
      
    },

    
  
    emits: ['promjenjena-selekcija']
  }
  

</script>
