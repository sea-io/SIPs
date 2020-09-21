package com.zilliqa.staking;


public class EventLogEntry {
    public String address;
    public String _eventname;
    public EventParam[] params;
    
    public EventLogEntry() {
        
    }
    
    public EventLogEntry(String address, String _eventname, EventParam[] params) {
        this.address = address;
        this._eventname = _eventname;
        this.params = params;
    }
    
}
