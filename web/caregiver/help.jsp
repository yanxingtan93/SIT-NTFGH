<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
  /* Style the buttons that are used to open and close the accordion panel */
  .accordion {
    background-color: #eee;
    color: #444;
    cursor: pointer;
    padding: 18px;
    width: 100%;
    text-align: left;
    border: none;
    outline: none;
    transition: 0.4s;
  }

  /* Add a background color to the button if it is clicked on (add the .active class with JS), and when you move the mouse over it (hover) */
  .active, .accordion:hover {
    background-color: #ccc;
  }

  /* Style the accordion panel. Note: hidden by default */
  .panel {
    padding: 28px 28px;
    background-color: white;
    display: none;
    overflow: hidden;
  }
  table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
  }

  td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
  }
</style>

<t:caregiverPage>
  <h1><i class="fa fa-question-circle"></i> Help</h1>
  <title>Help</title>
  <br>
  <h2>Frequently Asked Questions</h2>
  <br>
  <div class="faq">
    <button class="accordion">1.	What are the opening hours of the pharmacy?</button>
    <div class="panel">
      <p>
      <table>
        <tr>
          <td width="50%">Outpatient & Retail Pharmacy (Tower A)
            beside subway</td>
          <td width="50%">Mon - Fri: 8.30am - 7.00pm<br>
            Sat: 8.30am - 1.30pm<br>
            Closed on PH and Sundays<br>
          </td>
        </tr>
        <tr>
          <td>Discharge pharmacy (Tower B)</td>
          <td>Mon - Fri: 9.00am - 7.00pm<br>
            Sat, Sun & PH: 10.00am – 5.00pm<br>
          </td>
        </tr>
        <tr>
          <td>Retail pharmacy (Tower B) </td>
          <td>Mon - Fri: 9.00am - 7.00pm<br>
            Sat, Sun & PH: 10.00am - 2.30pm<br>
          </td>
        </tr>
        <tr>
          <td>Emergency pharmacy </td>
          <td>24 hours<br>
          </td>
        </tr>

        <tr>
          <td>Jurong community hospital (Tower C) pharmacy</td>
          <td>Mon - Fri: 8.30am - 6.00pm<br>
            Sat: 8.30am - 1.00pm<br>
            Sun & PH: Closed<br>
          </td>
        </tr>
        <tr>
          <td>Jurong Medical Centre Pharmacy (Boonlay)</td>
          <td>Mon-Fri: 9.00am – 5.30pm<br>
            Sat, Sun & PH: Closed<br>
          </td>
        </tr>
      </table>
      </p>
    </div>

    <button class="accordion">2.	Where can I collect my medications from?</button>
    <div class="panel">
      <p>
      <table>
        <tr>
          <td width="50%">Discharging from NTFGH tower B wards</td>
          <td width="50%">Discharge pharmacy (Tower B)<br>
            Located on Level 2 Tower B beside Kopitiam<br>
          </td>
        </tr>
        <tr>
          <td>Seen specialist outpatient clinic (SOC) doctor today at tower A level 3, 4, 5 or 7.</td>
          <td>You may proceed to <b>all</b> your outpatient clinic appointments for the day and collect your medications at the satellite pharmacy on the same level as your last outpatient clinic <br>
          </td>
        </tr>
        <tr>
          <td>Seen Urology/Renal doctor today</td>
          <td>Outpatient & Retail Pharmacy Level 2 (Tower A) beside subway<br>
            <br>
          </td>
        </tr>
        <tr>
          <td>Collecting remaining of prescription (did not see SOC doctor today)</td>
          <td>Outpatient & Retail Pharmacy Level 2 (Tower A) beside subway<br>
            <br>
          </td>
        </tr>
      </table>
      </p>
    </div>

    <button class="accordion">3.	Can I pre-order my medications?</button>
    <div class="panel">
      <p>
        Yes, you may if you are holding on to prescriptions with balance uncollected medication from Ng Teng Fong General Hospital’s Specialist Outpatient Clinics.<br>
        For more information, kindly click on the “Pre-order” tab.
      </p>
    </div>
  </div>


  <button class="accordion">4.	I have run out of medications before my next appointment. What should I do?</button>
  <div class="panel">
    <p>Please contact our General Enquiries Line at 6716 2000 (24 hours) to request for top-up. Thank you.<br>
      Note:<br>
      • We recommend that you contact the General Enquiries Line at least 1 week before you run out of medications.<br>
    </p>
  </div>

  <button class="accordion">5.	I have a question with regards to my medication. How do I contact a pharmacist?</button>
  <div class="panel">
    <p>You may send your enquiry through an email to JHCampus_OutpatientPharmacy@nuhs.edu.sg  . Thank you.</p>
  </div>
  <br><br>

  <h2>Contact Us</h2>
  <h4>General Enquiries Hotline</h4>
  <p>For general enquiries and topup of presciption medication: +65 6716 2000</p>
  <h4>Outpatient Pharmacy Email</h4>
  <p>For preorder purposes, kindly email to: <a href="mailto:JHCampus_OutpatientPharmacy@nuhs.edu.sg">JHCampus_OutpatientPharmacy@nuhs.edu.sg</a></p>
  <h2>Patient Education</h2>
  <p><a href="/PatientEducation.jsp">View patient education leaflets</a></p>

</t:caregiverPage>
<script>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            /* Toggle between adding and removing the "active" class,
            to highlight the button that controls the panel */
            this.classList.toggle("active");

            /* Toggle between hiding and showing the active panel */
            var panel = this.nextElementSibling;
            if (panel.style.display === "block") {
                panel.style.display = "none";
            } else {
                panel.style.display = "block";
            }
        });
    }
</script>
