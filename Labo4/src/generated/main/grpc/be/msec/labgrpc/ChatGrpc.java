package be.msec.labgrpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * De calculator service definitie
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.12.0)",
    comments = "Source: calculator.proto")
public final class ChatGrpc {

  private ChatGrpc() {}

  public static final String SERVICE_NAME = "tutorial.Chat";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendMessageMethod()} instead. 
  public static final io.grpc.MethodDescriptor<be.msec.labgrpc.Message,
      be.msec.labgrpc.Empty> METHOD_SEND_MESSAGE = getSendMessageMethodHelper();

  private static volatile io.grpc.MethodDescriptor<be.msec.labgrpc.Message,
      be.msec.labgrpc.Empty> getSendMessageMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<be.msec.labgrpc.Message,
      be.msec.labgrpc.Empty> getSendMessageMethod() {
    return getSendMessageMethodHelper();
  }

  private static io.grpc.MethodDescriptor<be.msec.labgrpc.Message,
      be.msec.labgrpc.Empty> getSendMessageMethodHelper() {
    io.grpc.MethodDescriptor<be.msec.labgrpc.Message, be.msec.labgrpc.Empty> getSendMessageMethod;
    if ((getSendMessageMethod = ChatGrpc.getSendMessageMethod) == null) {
      synchronized (ChatGrpc.class) {
        if ((getSendMessageMethod = ChatGrpc.getSendMessageMethod) == null) {
          ChatGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<be.msec.labgrpc.Message, be.msec.labgrpc.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "tutorial.Chat", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  be.msec.labgrpc.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  be.msec.labgrpc.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getReceiveMessageMethod()} instead. 
  public static final io.grpc.MethodDescriptor<be.msec.labgrpc.IsFirstMessage,
      be.msec.labgrpc.Message> METHOD_RECEIVE_MESSAGE = getReceiveMessageMethodHelper();

  private static volatile io.grpc.MethodDescriptor<be.msec.labgrpc.IsFirstMessage,
      be.msec.labgrpc.Message> getReceiveMessageMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<be.msec.labgrpc.IsFirstMessage,
      be.msec.labgrpc.Message> getReceiveMessageMethod() {
    return getReceiveMessageMethodHelper();
  }

  private static io.grpc.MethodDescriptor<be.msec.labgrpc.IsFirstMessage,
      be.msec.labgrpc.Message> getReceiveMessageMethodHelper() {
    io.grpc.MethodDescriptor<be.msec.labgrpc.IsFirstMessage, be.msec.labgrpc.Message> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = ChatGrpc.getReceiveMessageMethod) == null) {
      synchronized (ChatGrpc.class) {
        if ((getReceiveMessageMethod = ChatGrpc.getReceiveMessageMethod) == null) {
          ChatGrpc.getReceiveMessageMethod = getReceiveMessageMethod = 
              io.grpc.MethodDescriptor.<be.msec.labgrpc.IsFirstMessage, be.msec.labgrpc.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "tutorial.Chat", "receiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  be.msec.labgrpc.IsFirstMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  be.msec.labgrpc.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatMethodDescriptorSupplier("receiveMessage"))
                  .build();
          }
        }
     }
     return getReceiveMessageMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getRegisterMethod()} instead. 
  public static final io.grpc.MethodDescriptor<be.msec.labgrpc.RegisterRequest,
      be.msec.labgrpc.RegisterResponse> METHOD_REGISTER = getRegisterMethodHelper();

  private static volatile io.grpc.MethodDescriptor<be.msec.labgrpc.RegisterRequest,
      be.msec.labgrpc.RegisterResponse> getRegisterMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<be.msec.labgrpc.RegisterRequest,
      be.msec.labgrpc.RegisterResponse> getRegisterMethod() {
    return getRegisterMethodHelper();
  }

  private static io.grpc.MethodDescriptor<be.msec.labgrpc.RegisterRequest,
      be.msec.labgrpc.RegisterResponse> getRegisterMethodHelper() {
    io.grpc.MethodDescriptor<be.msec.labgrpc.RegisterRequest, be.msec.labgrpc.RegisterResponse> getRegisterMethod;
    if ((getRegisterMethod = ChatGrpc.getRegisterMethod) == null) {
      synchronized (ChatGrpc.class) {
        if ((getRegisterMethod = ChatGrpc.getRegisterMethod) == null) {
          ChatGrpc.getRegisterMethod = getRegisterMethod = 
              io.grpc.MethodDescriptor.<be.msec.labgrpc.RegisterRequest, be.msec.labgrpc.RegisterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "tutorial.Chat", "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  be.msec.labgrpc.RegisterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  be.msec.labgrpc.RegisterResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatMethodDescriptorSupplier("register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatStub newStub(io.grpc.Channel channel) {
    return new ChatStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatFutureStub(channel);
  }

  /**
   * <pre>
   * De calculator service definitie
   * </pre>
   */
  public static abstract class ChatImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * send a message
     * </pre>
     */
    public void sendMessage(be.msec.labgrpc.Message request,
        io.grpc.stub.StreamObserver<be.msec.labgrpc.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethodHelper(), responseObserver);
    }

    /**
     * <pre>
     * receive a message
     * </pre>
     */
    public void receiveMessage(be.msec.labgrpc.IsFirstMessage request,
        io.grpc.stub.StreamObserver<be.msec.labgrpc.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveMessageMethodHelper(), responseObserver);
    }

    /**
     * <pre>
     * register a user
     * </pre>
     */
    public void register(be.msec.labgrpc.RegisterRequest request,
        io.grpc.stub.StreamObserver<be.msec.labgrpc.RegisterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                be.msec.labgrpc.Message,
                be.msec.labgrpc.Empty>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getReceiveMessageMethodHelper(),
            asyncServerStreamingCall(
              new MethodHandlers<
                be.msec.labgrpc.IsFirstMessage,
                be.msec.labgrpc.Message>(
                  this, METHODID_RECEIVE_MESSAGE)))
          .addMethod(
            getRegisterMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                be.msec.labgrpc.RegisterRequest,
                be.msec.labgrpc.RegisterResponse>(
                  this, METHODID_REGISTER)))
          .build();
    }
  }

  /**
   * <pre>
   * De calculator service definitie
   * </pre>
   */
  public static final class ChatStub extends io.grpc.stub.AbstractStub<ChatStub> {
    private ChatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatStub(channel, callOptions);
    }

    /**
     * <pre>
     * send a message
     * </pre>
     */
    public void sendMessage(be.msec.labgrpc.Message request,
        io.grpc.stub.StreamObserver<be.msec.labgrpc.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * receive a message
     * </pre>
     */
    public void receiveMessage(be.msec.labgrpc.IsFirstMessage request,
        io.grpc.stub.StreamObserver<be.msec.labgrpc.Message> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveMessageMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * register a user
     * </pre>
     */
    public void register(be.msec.labgrpc.RegisterRequest request,
        io.grpc.stub.StreamObserver<be.msec.labgrpc.RegisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * De calculator service definitie
   * </pre>
   */
  public static final class ChatBlockingStub extends io.grpc.stub.AbstractStub<ChatBlockingStub> {
    private ChatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * send a message
     * </pre>
     */
    public be.msec.labgrpc.Empty sendMessage(be.msec.labgrpc.Message request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethodHelper(), getCallOptions(), request);
    }

    /**
     * <pre>
     * receive a message
     * </pre>
     */
    public java.util.Iterator<be.msec.labgrpc.Message> receiveMessage(
        be.msec.labgrpc.IsFirstMessage request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveMessageMethodHelper(), getCallOptions(), request);
    }

    /**
     * <pre>
     * register a user
     * </pre>
     */
    public be.msec.labgrpc.RegisterResponse register(be.msec.labgrpc.RegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * De calculator service definitie
   * </pre>
   */
  public static final class ChatFutureStub extends io.grpc.stub.AbstractStub<ChatFutureStub> {
    private ChatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * send a message
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<be.msec.labgrpc.Empty> sendMessage(
        be.msec.labgrpc.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethodHelper(), getCallOptions()), request);
    }

    /**
     * <pre>
     * register a user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<be.msec.labgrpc.RegisterResponse> register(
        be.msec.labgrpc.RegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;
  private static final int METHODID_RECEIVE_MESSAGE = 1;
  private static final int METHODID_REGISTER = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((be.msec.labgrpc.Message) request,
              (io.grpc.stub.StreamObserver<be.msec.labgrpc.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((be.msec.labgrpc.IsFirstMessage) request,
              (io.grpc.stub.StreamObserver<be.msec.labgrpc.Message>) responseObserver);
          break;
        case METHODID_REGISTER:
          serviceImpl.register((be.msec.labgrpc.RegisterRequest) request,
              (io.grpc.stub.StreamObserver<be.msec.labgrpc.RegisterResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return be.msec.labgrpc.TutorialProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Chat");
    }
  }

  private static final class ChatFileDescriptorSupplier
      extends ChatBaseDescriptorSupplier {
    ChatFileDescriptorSupplier() {}
  }

  private static final class ChatMethodDescriptorSupplier
      extends ChatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatFileDescriptorSupplier())
              .addMethod(getSendMessageMethodHelper())
              .addMethod(getReceiveMessageMethodHelper())
              .addMethod(getRegisterMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
