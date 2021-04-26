<template>
<el-input placeholder="Search table" v-model="search"></el-input>
  <el-table
    :ref="referenca"
    @row-click ="odabranDermatolog"
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
    <el-table-column
      property="rodjendan"
      label="Datum rodjenja"
      :formatter="formirajDatum"
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
      odabranDermatolog(row){
        this.$emit("promjenjena-selekcija",row)
      }
    },

    
  
    emits: ['promjenjena-selekcija']
  }
  

</script>
