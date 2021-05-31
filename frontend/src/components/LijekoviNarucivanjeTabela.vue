<template>
<el-input placeholder="Search table" v-model="search"></el-input>
  <el-table
    :ref="referenca"
    height="250"
    :data="lijekovi.filter(data => !pretraga || String(data.naziv).toLowerCase().includes(pretraga.toLowerCase()) ||
        String(data.vrstaLijeka).toLowerCase().includes(pretraga.toLowerCase())
        || String(data.ocijena).toLowerCase() === pretraga.toLowerCase()
      )"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
     >
    </el-table-column>
    
    <el-table-column
        property="naziv"
        label="Naziv"
        width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top">
            <template #default>
              <p>Naziv: {{ scope.row.naziv }}</p>
              <p>Vrsta: {{ scope.row.vrstaLijeka }}</p>
            </template>
            <template #reference>
              <div class="name-wrapper">
                <el-tag size="medium">{{ scope.row.naziv }}</el-tag>
              </div>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        property="vrstaLijeka"
        label="Vrsta Lijeka"
        width="180">
      </el-table-column>
      <el-table-column
        property="ocijena"
        :formatter="formirajOcjenu"
        label="Ocijena"
        width="180">
      </el-table-column>
      <el-table-column
        property="vrstaLijeka"
        label="Vrsta"
        width="180">
      </el-table-column>
      <el-table-column
        property="proizvodjac"
        label="Proizvodjac"
        width="180">
      </el-table-column>
      <el-table-column v-if="kolicina==='true'"
        property="kolicina"
        label="Kolicina"
        width="180">
      </el-table-column>
    
  </el-table>
  <div style="margin-top: 20px">
   
    
  </div>
</template>

<script>
export default {
  name: 'LijekoviNarucivanjeTabela',
  data() {
      return {
        multipleSelection: [],
        search : ''
      }
    },
    props: ['lijekovi','referenca','kolicina'],
    methods: {
     
      handleSelectionChange(val) {
        this.$emit("promjenjena-selekcija",val)
      },
      formirajOcjenu(val){
        if(val.ocijena==-1)
          return "Neocjenjen"
        return val.ocijena
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
