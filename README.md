# Blockchain
System Integration project
Description of the task: Create a decentralized peer to peer block chain.
* Provide a reproducible setup. That is, provide a Bash script, that can be run from any Linux machine, which instantiates your blockchain with some example nodes and which executes a test scenario on your blockchain.
* Provide a screencast demonstrating all functions of your running blockchain, with the help of the above script.
* Provide references to all sources that you used for your implementation.
* There must be at least four nodes.
Properties of the (minimum viable) block chain
1. Individual transactions are secured by PKI. 
o Transactions are authenticated: a malicious party can't masquerade as someone else and sign a transaction on their behalf. 
* Authentication is only with respect to the public-private keypair. There is no requirement for "strong authentication" that links the keypair to any other data about the participants. In fact, a single participant can generate and use multiple keypairs! In this sense, the network allows anonymous transactions.
o Non-repudiation: participants can't claim that the transaction did not happen after the fact.
o Integrity: transactions can't be modified after the fact.
2.Once created, transactions are broadcast into the P2P network. 
o Participants form a network where transactions and blocks are relayed amongst all the participating peers. There no central authority.
3.One or more transactions are aggregated into a "block".
o A block validates one or more transactions and claims the transaction fees. 
* This allow the transaction fees to remain small relative to the value of each transaction.
o A valid block must have a valid proof-of-work solution. 
* Valid proof-of-work output is hard to generate and cheap to verify.
* Proof-of-work is used to raise the cost of generating a valid block to impose a higher cost on running an attack against the network.
o Any peer is allowed to work on generating a valid block, and once a valid block is generated, it is broadcast into the network. 
* Any number of peers can compete to generate a valid block. There is no coordination. When a fork is detected, it is resolved by automatically switching to the longest chain.
o Each block contains a link to the previous valid block, allowing us to traverse the full history of all recorded transactions in the network.
4.Peers listen for new block announcements and merge them into their ledgers.
o Inclusion of the transaction in a block acts as a "confirmation" of that transaction, but that fact alone does not "finalize" any transaction. Instead, we rely on the length of the chain as a proxy for "safety" of the transaction. Each participant can choose their own level of risk tolerance, ranging from 0-confirmation transactions to waiting for any arbitrary number of blocks.

Functionalities:

-Transfer value:
Transaction documents are signed by peers with the private key of his PKI pair. The signed document is send out for confirmation from a block miner.
Documents looks like:
From: Alice To: Bob Amount: 10 Timestamp: 123423

-Create blocks:
Miners(any peer) collect signed transactions to create blocks. For block to be valid it must contain proof-of-work output. The output should start with n-number of zeros. Once generated it’s broadcast to the network.

-Current ledger:
Peers listen for blocks on the network. They include them in their ledgers

-Switch chain:
Peers receive blocks via the network. If a block from new chain is detected. The length of both chains is calculated and the longest one is taken as the current.

