package uma.hudss.Model;



public class ContactModel {
	
public String ContactPhoneNumber;
public String ContactName;
public String MaxNumberOfCalls;


public ContactModel( String name ,String phoneNumber,String maxNumberOfcalls)
{
	this.ContactName =name;
	this.ContactPhoneNumber =phoneNumber;
	this.MaxNumberOfCalls = maxNumberOfcalls;
}
public void SetContactName(String name){
	this.ContactName =name;
}
public String GetContactName(){
	return ContactName;
}
public void SetPhoneNumber(String number){
	this.ContactPhoneNumber =number;
}
public String GetPhoneNumber(){
	return ContactPhoneNumber;
}
public void SetMaximumNumberOfCalls(String MaxNumberOfCalls ){
	this.MaxNumberOfCalls =MaxNumberOfCalls;
}
public String GetMaximumNumberOfCalls(){
	return MaxNumberOfCalls;
}

}
