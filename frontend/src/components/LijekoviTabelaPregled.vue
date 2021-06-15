<template>
<el-input placeholder="Search table" v-model="search"></el-input>
  <el-table
    :ref="referenca"
    height="250"
    :data="lijekovi.filter(data => !search || String(data.naziv).toLowerCase().includes(search.toLowerCase()) ||
    String(data.sifra).toLowerCase().includes(search.toLowerCase()) ||
    String(data.vrstaLijeka).toLowerCase().includes(search.toLowerCase()) ||
    String(data.oblikLijeka).toLowerCase().includes(search.toLowerCase()) ||
    String(data.sastav).toLowerCase().includes(search.toLowerCase())
    )"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
     >
    </el-table-column>
    
    <el-table-column
      property="sifra"
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
      property="napomena"
      label="Napomena"
     
     >
    </el-table-column>


    <el-table-column 
      
      label="Kolicina"
      prop="kolicina"
     >
       <template v-slot="scope">
            <el-input-number v-model="scope.row.kolicina" :min="1" :max="99" size="small" controls-position="right" />
        </template>
    </el-table-column>
    
  </el-table>
  <div style="margin-top: 20px">
   
    
  </div>
</template>

<script>
export default {
  name: 'LijekoviTabela',
  data() {
    
      return {
        multipleSelection: [],
        search : ''
      }
    },
    props: ['lijekovi','referenca'],
    methods: {
     
      handleSelectionChange(val) {
        this.$emit("promjenjena-selekcija",val)
      },
      formatirajProizvodnju(row){
      
      if(row.prodaja){
        return "Prodaja"
      }
      else{
        return "Magacin"
      }
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
