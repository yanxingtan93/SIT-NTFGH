<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:patientPage>
  <div class="row">
    <div class="col-sm-6">
      <h2>Self-Collection</h2>
      <p>
        Venue:<br>
        Ng Teng Fong General Hospital<br>
        Tower A, Level 2, Outpatient & Retail<br>
        Pharmacy<br><br>
        Operating Hours:<br>
        Monday - Friday: 8.30 am - 7 pm<br>
        Saturday: 8.30 am - 1.30 pm<br>
        Closed on Sundays / Public Holiday<br>
      </p>
    </div>
    <div class="col-sm-6">
      <h2>Delivery</h2>
      <p>
        Delivery Charge: $10.70<br><br>
        Delivery Time:<br>
        Monday - Friday: 9 am - 5pm <br>(excluding public holiday & eve of public holiday)<br><br><br>
        Pharmacy will contact you to arrange time.<br><br>
      </p>

    </div>
  </div>
  <br><h1>Current Preorder(s)</h1><br>
  <div class="row">
    <table class="dailyMedTable table table-striped table-bordered">
      <thead class="thead-dark">
      <tr>
        <th style="width:15%">Date</th>
        <th style="width:60%">Medication</th>
        <th style="width:25%">Collection Type</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td><img src="https://img.tesco.com/Groceries/pi/718/5000158100718/IDShot_540x540.jpg" height="150px"></td>
        <td>
          <div class="pillbox">
            <h2>Ibuprofen</h2>
            <h4>15 pills</h4>
          </div>
        </td>
        <td><h3>Home Delivery</h3></td>
      </tr>
      </tbody>
    </table>
  </div>
  <br><h1>New Preorder</h1><br>
  <form>
    <div class="form-group row">
      <label class="col-sm-2 col-form-label">Medication</label>
      <div class="col-sm-4">
        <select name="meds">
          <option value="1">Ibuprofen</option>
          <option value="2">Phenphedrin</option>
          <option value="3">Alprazolam</option>
        </select>
      </div>
      <label class="col-sm-2 col-form-label">Total Quantity</label>
      <div class="col-sm-4">
        <input type="number" name="quantity" min="1" max="5">
      </div>
    </div>
    <br>
    <div class="form-group row">
      <div class="col-sm-12">
        <button type="button" class="btn btn-success btn-block btn-lg" onclick="openConfirmModal(0)">Submit</button>
      </div>
    </div>
  </form>
  <div id="confirmModal" class="modal">
    <div class="modal-content">
      <h2>Please confirm.</h2><br>
      <div class="row">
        <div class="col-sm-6">
          <button type="button" class="btn btn-success btn-block btn-lg" onclick="">Confirm</button>
        </div>
        <div class="col-sm-6">
          <button type="button" class="btn btn-default btn-block btn-lg"  onclick="closeConfirmModal()">Cancel</button>
        </div>
      </div>
    </div>
  </div>
  <script>
      // Get the modal
      var confirmModal = document.getElementById('confirmModal');

      // Get the button that opens the modal
      function openConfirmModal(id) {
          confirmModal.style.display = "block";
      }

      // When the user clicks on <span> (x), close the modal
      function closeConfirmModal() {
          confirmModal.style.display = "none";
      }
      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function(event) {
          if (event.target == confirmModal) {
              confirmModal.style.display = "none";
          }
      }
  </script>
</t:patientPage>
