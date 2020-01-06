package org.fisco.bcos.temp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple5;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class FISCO extends Contract {
    public static String BINARY = "6080604052600060035534801561001557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550612d73806100656000396000f3006080604052600436106100cf576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168062ce8e3e146100d457806313af403514610140578063208f3619146101835780636f77926b146101e45780638da5cb5b14610317578063902b80491461036e57806392f98170146103cf578063adb80efb1461043a578063b63e6ac3146104d2578063cfb020ef1461058f578063d7554a6814610627578063f04cf50a14610736578063f849f379146107af578063fc6bd6dc14610832575b600080fd5b3480156100e057600080fd5b506100e961085d565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561012c578082015181840152602081019050610111565b505050509050019250505060405180910390f35b34801561014c57600080fd5b50610181600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108eb565b005b34801561018f57600080fd5b506101ce600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506109f2565b6040518082815260200191505060405180910390f35b3480156101f057600080fd5b50610225600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d17565b60405180806020018060200184151515158152602001838103835286818151815260200191508051906020019080838360005b83811015610273578082015181840152602081019050610258565b50505050905090810190601f1680156102a05780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156102d95780820151818401526020810190506102be565b50505050905090810190601f1680156103065780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561032357600080fd5b5061032c610f35565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561037a57600080fd5b506103b9600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610f5a565b6040518082815260200191505060405180910390f35b3480156103db57600080fd5b50610424600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291908035906020019092919050505061139d565b6040518082815260200191505060405180910390f35b34801561044657600080fd5b5061047b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611aa3565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156104be5780820151818401526020810190506104a3565b505050509050019250505060405180910390f35b3480156104de57600080fd5b506104fd60048036038101908080359060200190929190505050611b3d565b604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184815260200183151515158152602001821515151581526020019550505050505060405180910390f35b34801561059b57600080fd5b506105d0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611c25565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156106135780820151818401526020810190506105f8565b505050509050019250505060405180910390f35b34801561063357600080fd5b506106f4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611cbf565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561074257600080fd5b5061078b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291908035906020019092919050505061201c565b60405180848152602001838152602001828152602001935050505060405180910390f35b3480156107bb57600080fd5b506107f0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506126a9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561083e57600080fd5b50610847612922565b6040518082815260200191505060405180910390f35b606060048054806020026020016040519081016040528092919081815260200182805480156108e157602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610897575b5050505050905090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156109af576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f6e6f207065726d697373696f6e0000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff161515610ab8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f6e6f74206c6f67696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff161515610b7c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f6265656e20666f7262696464656e00000000000000000000000000000000000081525060200191505060405180910390fd5b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff161515610c40576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f75736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff161515610d04576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f7573657220697320666f7262696464656e00000000000000000000000000000081525060200191505060405180910390fd5b610d0f33848461292f565b905092915050565b6060806000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff16828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e855780601f10610e5a57610100808354040283529160200191610e85565b820191906000526020600020905b815481529060010190602001808311610e6857829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f215780601f10610ef657610100808354040283529160200191610f21565b820191906000526020600020905b815481529060010190602001808311610f0457829003601f168201915b505050505091509250925092509193909250565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff161515611020576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f6e6f74206c6f67696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff1615156110e4576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f6265656e20666f7262696464656e00000000000000000000000000000000000081525060200191505060405180910390fd5b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff1615156111a8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f75736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff16151561126c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f7573657220697320666f7262696464656e00000000000000000000000000000081525060200191505060405180910390fd5b6003548210151561127c57600080fd5b6002600083815260200190815260200160002060030160009054906101000a900460ff161515156112ac57600080fd5b8273ffffffffffffffffffffffffffffffffffffffff166002600084815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561131c57600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166002600084815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561138c57600080fd5b61139582612af6565b905092915050565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff161515611463576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f6e6f74206c6f67696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff161515611527576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f6265656e20666f7262696464656e00000000000000000000000000000000000081525060200191505060405180910390fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff1615156115eb576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f75736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff1615156116af576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f7573657220697320666f7262696464656e00000000000000000000000000000081525060200191505060405180910390fd5b6117c8600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156117885780601f1061175d57610100808354040283529160200191611788565b820191906000526020600020905b81548152906001019060200180831161176b57829003601f168201915b50505050506040805190810160405280600481526020017f62616e6b00000000000000000000000000000000000000000000000000000000815250612b5e565b15156117d357600080fd5b600354821015156117e357600080fd5b6002600083815260200190815260200160002060030160009054906101000a900460ff1615151561181357600080fd5b6002600083815260200190815260200160002060030160019054906101000a900460ff1615151561184357600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166002600084815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156118b357600080fd5b826002600084815260200190815260200160002060020154101515156118d857600080fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff16151561199c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f6f726967696e616c2075736572206e6f7420657869737400000000000000000081525060200191505060405180910390fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff161515611a60576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807f6f726967696e616c207573657220697320666f7262696464656e00000000000081525060200191505060405180910390fd5b60016002600084815260200190815260200160002060030160016101000a81548160ff021916908315150217905550611a9a33858561292f565b90509392505050565b6060600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201805480602002602001604051908101604052809291908181526020018280548015611b3157602002820191906000526020600020905b815481526020019060010190808311611b1d575b50505050509050919050565b60008060008060006002600087815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166002600088815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166002600089815260200190815260200160002060020154600260008a815260200190815260200160002060030160009054906101000a900460ff16600260008b815260200190815260200160002060030160019054906101000a900460ff169450945094509450945091939590929450565b6060600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600301805480602002602001604051908101604052809291908181526020018280548015611cb357602002820191906000526020600020905b815481526020019060010190808311611c9f575b50505050509050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611d85576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f6e6f207065726d697373696f6e0000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff16151515611e4a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f616464726573732069732075736564000000000000000000000000000000000081525060200191505060405180910390fd5b82600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000019080519060200190611ea0929190612ca2565b5081600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001019080519060200190611ef7929190612ca2565b5060018060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160006101000a81548160ff02191690831515021790555060018060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160016101000a81548160ff02191690831515021790555060048490806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550508390509392505050565b600080600080600080600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff1615156120e9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f6e6f74206c6f67696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff1615156121ad576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f6265656e20666f7262696464656e00000000000000000000000000000000000081525060200191505060405180910390fd5b600160008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff161515612271576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f75736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b600160008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff161515612335576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f7573657220697320666f7262696464656e00000000000000000000000000000081525060200191505060405180910390fd5b6003548710151561234557600080fd5b6002600088815260200190815260200160002060030160009054906101000a900460ff1615151561237557600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166002600089815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156123e557600080fd5b8760026000898152602001908152602001600020600201541015151561240a57600080fd5b600160008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff1615156124ce576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f6f726967696e616c2075736572206e6f7420657869737400000000000000000081525060200191505060405180910390fd5b600160008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160009054906101000a900460ff161515612592576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807f6f726967696e616c207573657220697320666f7262696464656e00000000000081525060200191505060405180910390fd5b61259b87612af6565b92506125de6002600089815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168a8a61292f565b9150600090508760026000898152602001908152602001600020600201541115612694576126916002600089815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600260008a815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168a600260008c8152602001908152602001600020600201540361292f565b90505b82828295509550955050505093509350939050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561276f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f6e6f207065726d697373696f6e0000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160019054906101000a900460ff161515612833576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f61646472657373206e6f7420757365000000000000000000000000000000000081525060200191505060405180910390fd5b6040805190810160405280600681526020017f666f726269640000000000000000000000000000000000000000000000000000815250600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010190805190602001906128be929190612ca2565b506000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160006101000a81548160ff021916908315150217905550819050919050565b6000600480549050905090565b60006003600081548092919060010191905055508360026000600354815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508260026000600354815260200190815260200160002060010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160026000600354815260200190815260200160002060020181905550600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002016003549080600181540180825580915050906001820390600052602060002001600090919290919091505550600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600301600354908060018154018082558091505090600182039060005260206000200160009091929091909150555060035490509392505050565b600060016002600084815260200190815260200160002060030160006101000a81548160ff02191690831515021790555060016002600084815260200190815260200160002060030160016101000a81548160ff021916908315150217905550819050919050565b60008082518451141515612b755760009150612c9b565b600090505b8351811015612c96578281815181101515612b9157fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168482815181101515612c0c57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141515612c895760009150612c9b565b8080600101915050612b7a565b600191505b5092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10612ce357805160ff1916838001178555612d11565b82800160010185558215612d11579182015b82811115612d10578251825591602001919060010190612cf5565b5b509050612d1e9190612d22565b5090565b612d4491905b80821115612d40576000816000905550600101612d28565b5090565b905600a165627a7a72305820710b68650bc94acef77929d9bd2f3c304cf4c5e6c2014c4b594a9caad003778d0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"getUsers\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"other\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"genReceipt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_useraddr\",\"type\":\"address\"}],\"name\":\"getUser\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"payReceipt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"},{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"reqReceipt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_useraddr\",\"type\":\"address\"}],\"name\":\"getReceiptOuts\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"getReceipt\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_useraddr\",\"type\":\"address\"}],\"name\":\"getReceiptIns\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_useraddr\",\"type\":\"address\"},{\"name\":\"_username\",\"type\":\"string\"},{\"name\":\"_usergroup\",\"type\":\"string\"}],\"name\":\"addUser\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"},{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"splitReceipt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_useraddr\",\"type\":\"address\"}],\"name\":\"delUser\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getUsersNum\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final String FUNC_GETUSERS = "getUsers";

    public static final String FUNC_SETOWNER = "setOwner";

    public static final String FUNC_GENRECEIPT = "genReceipt";

    public static final String FUNC_GETUSER = "getUser";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAYRECEIPT = "payReceipt";

    public static final String FUNC_REQRECEIPT = "reqReceipt";

    public static final String FUNC_GETRECEIPTOUTS = "getReceiptOuts";

    public static final String FUNC_GETRECEIPT = "getReceipt";

    public static final String FUNC_GETRECEIPTINS = "getReceiptIns";

    public static final String FUNC_ADDUSER = "addUser";

    public static final String FUNC_SPLITRECEIPT = "splitReceipt";

    public static final String FUNC_DELUSER = "delUser";

    public static final String FUNC_GETUSERSNUM = "getUsersNum";

    @Deprecated
    protected FISCO(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected FISCO(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected FISCO(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected FISCO(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<List> getUsers() {
        final Function function = new Function(FUNC_GETUSERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setOwner(String other) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(other)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setOwner(String other, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(other)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setOwnerSeq(String other) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(other)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> genReceipt(String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_GENRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void genReceipt(String _to, BigInteger _amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GENRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String genReceiptSeq(String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_GENRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple3<String, String, Boolean>> getUser(String _useraddr) {
        final Function function = new Function(FUNC_GETUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple3<String, String, Boolean>>(
                new Callable<Tuple3<String, String, Boolean>>() {
                    @Override
                    public Tuple3<String, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> payReceipt(String _from, BigInteger _id) {
        final Function function = new Function(
                FUNC_PAYRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void payReceipt(String _from, BigInteger _id, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PAYRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String payReceiptSeq(String _from, BigInteger _id) {
        final Function function = new Function(
                FUNC_PAYRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> reqReceipt(String _to, BigInteger _amount, BigInteger _id) {
        final Function function = new Function(
                FUNC_REQRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void reqReceipt(String _to, BigInteger _amount, BigInteger _id, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REQRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String reqReceiptSeq(String _to, BigInteger _amount, BigInteger _id) {
        final Function function = new Function(
                FUNC_REQRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getReceiptOuts(String _useraddr) {
        final Function function = new Function(FUNC_GETRECEIPTOUTS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<Tuple5<String, String, BigInteger, Boolean, Boolean>> getReceipt(BigInteger _id) {
        final Function function = new Function(FUNC_GETRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple5<String, String, BigInteger, Boolean, Boolean>>(
                new Callable<Tuple5<String, String, BigInteger, Boolean, Boolean>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, BigInteger, Boolean, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<List> getReceiptIns(String _useraddr) {
        final Function function = new Function(FUNC_GETRECEIPTINS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addUser(String _useraddr, String _username, String _usergroup) {
        final Function function = new Function(
                FUNC_ADDUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_username), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_usergroup)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addUser(String _useraddr, String _username, String _usergroup, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_username), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_usergroup)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addUserSeq(String _useraddr, String _username, String _usergroup) {
        final Function function = new Function(
                FUNC_ADDUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_username), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_usergroup)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> splitReceipt(String _to, BigInteger _amount, BigInteger _id) {
        final Function function = new Function(
                FUNC_SPLITRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void splitReceipt(String _to, BigInteger _amount, BigInteger _id, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SPLITRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String splitReceiptSeq(String _to, BigInteger _amount, BigInteger _id) {
        final Function function = new Function(
                FUNC_SPLITRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> delUser(String _useraddr) {
        final Function function = new Function(
                FUNC_DELUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void delUser(String _useraddr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DELUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String delUserSeq(String _useraddr) {
        final Function function = new Function(
                FUNC_DELUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_useraddr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getUsersNum() {
        final Function function = new Function(FUNC_GETUSERSNUM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static FISCO load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new FISCO(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static FISCO load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new FISCO(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static FISCO load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new FISCO(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static FISCO load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new FISCO(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<FISCO> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(FISCO.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<FISCO> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(FISCO.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<FISCO> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(FISCO.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<FISCO> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(FISCO.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
