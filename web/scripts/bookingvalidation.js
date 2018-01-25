/* 
 * SOURCE: MURACH BOOK APPLICATION "ch07emailValidatePlus"
 * Java Servlets and JSP, Official text book for HNC year
 */

function validate(form)
{
    /* */
    if (form.outboundFlightId.value==="")
    {
        alert("Please choose an outbound flight");
        form.outboundFlightId.focus();
    } 
    else if (form.returnFlightId.value==="")
    {
        alert("Please choose a return flight");
        form.returnFlightId.focus();
    }
    else 
    {
        form.submit();
    }
}