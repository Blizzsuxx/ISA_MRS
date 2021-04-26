 <template>
  <teleport to="body">
    <div v-if="modalOpen" id="myModal" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <span @click="modalOpen = false" class="close">&times;</span>
          <h2>Izmjena cijene lijeka</h2>
        </div>
        <div class="modal-body">
          <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="lijek"
          >
            <el-form-item label="Cijena">
              <el-input-number
                v-model="lijek.cijena"
                :precision="2"
                :step="0.1"
                :max="10000000"
              ></el-input-number>
            </el-form-item>
            <el-form-item label="Datum isteka">
              <el-date-picker
                v-model="lijek.datumIstekaCijene"
                type="date"
                placeholder="Izaberite vrijeme isteka cijene"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="promjeniCijenu"
                >Ažuriraj cijenu</el-button
              >
              
            </el-form-item>
          </el-form>
        </div>
        <!-- <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div> -->
      </div>
    </div>
  </teleport>
</template>

<script>
export default {
  name: "ModalniProzorCijena",
  data() {
    return {
      modalOpen: false,
      lijek: {
        id:-1,
        cijena: 0,
        datumIstekaCijene: new Date(),
      },
    };
  },
  methods: {
      promjeniCijenu(){
          console.log("andrija je najajci")
          this.$store.dispatch("APlijekovi/promjeniCijenu",this.lijek)
      }
  },
  mount() {
    
  },
};
</script>

<style scoped>
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 300px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  border: 1px solid #888;
  width: 30%;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s;
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

@keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

/* The Close Button */
.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 10px 16px;
  background-color: #409eff;
  color: white;
}

.modal-body {
  padding: 2px 16px;
}

.modal-footer {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}
</style>