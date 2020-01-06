pragma solidity ^0.4.24;

contract FISCO {
    address public owner;
    constructor() public {
        owner = msg.sender;
    }
    modifier onlyOwner() {
        require(msg.sender == owner, 'no permission');
        _;
    }
    function setOwner(address other) public onlyOwner {
        owner = other;
    }
    // ------------------------------------------------

    struct User {
        string username;
        string usergroup;
        uint[] receiptOuts;
        uint[] receiptIns;
        bool isValid;
        bool isUsed;
    }

    struct Receipt {
        address from;
        address to;
        uint amount;
        bool isCheck;
        bool isUsed;
    }

    mapping(address => User) users;
    mapping(uint => Receipt) receipts;
    uint numReceipt = 0;

    address[] userArr;

    // ========== String Tool ==========
    function isEqual(string a, string b) internal pure returns (bool) {
        if (bytes(a).length != bytes(b).length) {
            return false;
        }
        for (uint i = 0; i < bytes(a).length; i ++) {
            if(bytes(a)[i] != bytes(b)[i]) {
                return false;
            }
        }
        return true;
    }

    // ========== Get_Method ==========
    function getUser(address _useraddr) public view returns (string, string, bool) {
        return (users[_useraddr].username, users[_useraddr].usergroup, users[_useraddr].isUsed);
    }
    function getUsers() public view returns (address[]) {
        return userArr;
    }
    function getUsersNum() public view returns (uint) {
        return userArr.length;
    }
    function getReceiptOuts(address _useraddr) public view returns (uint[]) {
        return users[_useraddr].receiptOuts;
    }

    function getReceiptIns(address _useraddr) public view returns (uint[]) {
        return users[_useraddr].receiptIns;
    }
    function getReceipt(uint _id) public view returns (address, address, uint, bool, bool) {
        return (receipts[_id].from, receipts[_id].to, receipts[_id].amount, receipts[_id].isCheck, receipts[_id].isUsed);
    }

    // ========== Add / Del User ==========
    function addUser(address _useraddr, string _username, string _usergroup) public onlyOwner returns (address) {
        require(!users[_useraddr].isUsed, 'address is used');
        users[_useraddr].username = _username;
        users[_useraddr].usergroup = _usergroup;
        users[_useraddr].isValid = true;
        users[_useraddr].isUsed = true;
        userArr.push(_useraddr);
        return _useraddr;
    }
    function delUser(address _useraddr) public onlyOwner returns (address) {
        require(users[_useraddr].isUsed, 'address not use');
        users[_useraddr].usergroup = "forbid";
        users[_useraddr].isValid = false;
        return _useraddr;
    }

    // ========== Add / Del Receipt ==========
    function addReceipt(address _from, address _to, uint _amount) private returns (uint) {
        numReceipt ++;
        receipts[numReceipt].from = _from;
        receipts[numReceipt].to = _to;
        receipts[numReceipt].amount = _amount;

        users[_from].receiptOuts.push(numReceipt);
        users[_to].receiptIns.push(numReceipt);
        return numReceipt;
    }
    function delReceipt(uint _id) private returns (uint) {
        receipts[_id].isCheck = true;
        receipts[_id].isUsed = true;
        return _id;
    }

    // ========== Gen / Split / Req / Pay Receipt ==========
    function genReceipt(address _to, uint _amount) public returns (uint){
        require(users[msg.sender].isUsed, 'not login');
        require(users[msg.sender].isValid, 'been forbidden');
        require(users[_to].isUsed, 'user not exist');
        require(users[_to].isValid, 'user is forbidden');
        return addReceipt(msg.sender, _to, _amount);
    }
    function splitReceipt(address _to, uint _amount, uint _id) public returns (uint, uint, uint) {
        require(users[msg.sender].isUsed, 'not login');
        require(users[msg.sender].isValid, 'been forbidden');
        require(users[_to].isUsed, 'user not exist');
        require(users[_to].isValid, 'user is forbidden');
        require(_id < numReceipt);
        require(!receipts[_id].isCheck);
        require(receipts[_id].to == msg.sender);
        require(receipts[_id].amount >= _amount);
        require(users[_to].isUsed, 'original user not exist');
        require(users[_to].isValid, 'original user is forbidden');
        uint id0 = delReceipt(_id);
        uint id1 = addReceipt(receipts[_id].from, _to, _amount);
        uint id2 = 0;
        if (receipts[_id].amount > _amount) {
            id2 = addReceipt(receipts[_id].from, receipts[_id].to, receipts[_id].amount - _amount);
        }
        return (id0, id1, id2);
    }
    function reqReceipt(address _to, uint _amount, uint _id) public returns (uint) {
        require(users[msg.sender].isUsed, 'not login');
        require(users[msg.sender].isValid, 'been forbidden');
        require(users[_to].isUsed, 'user not exist');
        require(users[_to].isValid, 'user is forbidden');
        require(isEqual(users[_to].usergroup, "bank"));
        require(_id < numReceipt);
        require(!receipts[_id].isCheck);
        require(!receipts[_id].isUsed);
        require(receipts[_id].to == msg.sender);
        require(receipts[_id].amount >= _amount);
        require(users[_to].isUsed, 'original user not exist');
        require(users[_to].isValid, 'original user is forbidden');
        receipts[_id].isUsed = true;
        return addReceipt(msg.sender, _to, _amount);
    }
    function payReceipt(address _from, uint _id) public returns (uint) {
        require(users[msg.sender].isUsed, 'not login');
        require(users[msg.sender].isValid, 'been forbidden');
        require(users[_from].isUsed, 'user not exist');
        require(users[_from].isValid, 'user is forbidden');
        require(_id < numReceipt);
        require(!receipts[_id].isCheck);
        require(receipts[_id].from == _from);
        require(receipts[_id].to == msg.sender);
        return delReceipt(_id);
    }
}
